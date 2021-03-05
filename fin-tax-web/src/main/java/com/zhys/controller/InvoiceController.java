package com.zhys.controller;


import com.alibaba.fastjson.JSONObject;
import com.lycheeframework.core.model.IPO;
import com.zhys.invoice.po.InvoiceOriginalLine;
import com.zhys.invoice.po.InvoiceSplitLine;
import com.zhys.po.InvoiceHeadDto;
import com.zhys.po.OriginalBodyDto;
import com.zhys.po.OriginalHeadDto;
import com.zhys.result.ResponseResult;
import com.zhys.service.*;
import com.zhys.invoice.po.Customer;
import com.zhys.util.HttpPostUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.invoice.po.InvoiceHead;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("invoice-api")
@ResponseResult
@CrossOrigin
public class InvoiceController {


    @Autowired
    private SQLManager manager;

    @Autowired
    private InvoiceHeadService service;

    @Autowired
    private InvoiceSplitLineService splitLineService;

    @Autowired
    private OriginalHeadService originalHeadService;

    @Autowired
    private OriginalBodyService originalBodyService;

//	@Autowired
//	private CustomerService customerService;

    private static final String INDEX = "index";
//


    /**
     * 主要逻辑
     * 主要是以下几点：1：通过invoice-api/page   doc_status=0 获取待开票信息引入开票，2、引入后可以补充必要信息，比如发票类型  3、调用invoice-api/submit提交开票
     */


    @ApiOperation(value = "原始单据头分页列表", notes = "原始单据头分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "当前第几页", required = true, paramType = "query", dataType = "Integer"),
    })
    @PostMapping(value = "/original/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Pages<List<InvoiceHead>> originalPages(@RequestParam Integer pageSize, @RequestParam Integer pageNum, @ApiParam(name = "invoiceHead", value = "查询条件", required = true)
    @RequestBody(required = false) InvoiceHead invoiceHead) {
//		invoiceHead.setDocStatus("-1");
        return originalHeadService.pages(invoiceHead, pageSize, pageNum);
    }


    @ApiOperation(value = "通过单据编号获取原始单据明细信息", notes = "通过单据编号获取原始单据明细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docOrignum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/original/bodyList")
    public @ResponseBody
    List<OriginalBodyDto> queryList(@RequestParam String docOrignum) {
        OriginalBodyDto originalBodyDto = new OriginalBodyDto();
        originalBodyDto.setDocOrignum(docOrignum);
        return originalBodyService.queryList(originalBodyDto);
    }

//    @ApiOperation(value = "发票头分页列表", notes = "根据条件查询数据并分页")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "pageNum", value = "当前第几页", required = true, paramType = "query", dataType = "Integer"),
//    })
//    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    Pages<List<OriginalHeadDto>> pages(@RequestParam Integer pageSize, @RequestParam Integer pageNum, @ApiParam(name = "originalHeadDto", value = "查询条件", required = true)
//    @RequestBody(required = false) OriginalHeadDto originalHeadDto) {
//        return originalHeadService.pages(originalHeadDto, pageSize, pageNum);
//    }
//

    @ApiOperation(value = "保存信息", notes = "保存信息")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Object save(@RequestBody InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        Integer re = service.save(invoiceHead, "0");

        if (re < 1) {
            //throw new BusinessException("保存失败！");
            jsonObject.put("msg", "保存失败");
            jsonObject.put("docNum", "");
            jsonObject.put("success", false);
        } else {
            jsonObject.put("msg", "保存成功");
            jsonObject.put("docNum", invoiceHead.getDocNum());
            jsonObject.put("orgName", invoiceHead.getOrgName());
            jsonObject.put("userName", invoiceHead.getUserName());
            jsonObject.put("checkName", invoiceHead.getCheckName());
            jsonObject.put("docDate", invoiceHead.getDocDate());
            jsonObject.put("success", true);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }


    @ApiOperation(value = "提交信息", notes = "提交信息")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Object submit(@RequestBody InvoiceHead invoiceHead) {

        Integer re = service.save(invoiceHead, "1");
        JSONObject jsonObject = new JSONObject();
        if (re < 1) {
            //throw new BusinessException("保存失败！");
            jsonObject.put("msg", "提交失败");
            jsonObject.put("docNum", invoiceHead.getDocNum());
            jsonObject.put("success", false);
            jsonObject.put("docStatus", "0");
        } else {
            jsonObject.put("msg", "提交成功");
            jsonObject.put("docNum", invoiceHead.getDocNum());
            jsonObject.put("docStatus", "1");
            jsonObject.put("orgName", invoiceHead.getOrgName());
            jsonObject.put("userName", invoiceHead.getUserName());
            jsonObject.put("checkName", invoiceHead.getCheckName());
            jsonObject.put("docDate", invoiceHead.getDocDate());
            jsonObject.put("billDate", invoiceHead.getBillDate());
            jsonObject.put("success", true);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }


    @ApiOperation(value = "通过单据编号获取信息", notes = "通过单据编号获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "path", dataType = "String"),
    })
    @GetMapping("/info/{docNum}")
    public InvoiceHead info(@PathVariable("docNum") String docNum, @ApiIgnore InvoiceHead invoiceHead) {
        invoiceHead.setDocNum(docNum);
        invoiceHead = service.queryByEntity(invoiceHead);

        return invoiceHead;
    }

//    @PostMapping("lists")
//	public @ResponseBody List<InvoiceHead> queryList(@RequestBody InvoiceHead invoiceHead){
//		return  service.queryList(invoiceHead);
//	}


    /**
     * 取消提交
     *
     * @param docNum
     * @return
     */
    @ApiOperation(value = "取消提交", notes = "取消提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/cancelCommit")
    public Object cancelCommit(@RequestParam("docNum") String docNum, @ApiIgnore InvoiceHead invoiceHead) {
        if (StringUtils.isEmpty(docNum)) {
            return 0;
        }
        InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
        invoiceSplitLine.setDocNum(docNum);
        Integer re = splitLineService.cancelCommit(invoiceSplitLine);
        JSONObject jsonObject = new JSONObject();
        if (re < 1) {
            //throw new BusinessException("保存失败！");
            jsonObject.put("msg", "取消提交失败");
            jsonObject.put("docNum", "");
            jsonObject.put("docStatus", "0");
            jsonObject.put("success", false);
        } else {
            jsonObject.put("msg", "取消提交成功");
            jsonObject.put("docNum", invoiceSplitLine.getDocNum());
            jsonObject.put("docStatus", "0");
            invoiceHead.setDocNum(docNum);
            jsonObject.put("success", true);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }


    /**
     * 整单作废
     *
     * @param docNum
     * @return
     */
    @ApiOperation(value = "整单作废", notes = "整单作废")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/abolishAll")
    public Object abolishAll(@RequestParam("docNum") String docNum, @ApiIgnore InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(docNum)) {
            jsonObject.put("msg", "整单作废失败");
            jsonObject.put("docStatus", "2");
            jsonObject.put("success", false);
        } else {
            InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
            invoiceSplitLine.setDocNum(docNum);
            splitLineService.abolishAll(invoiceSplitLine);
            jsonObject.put("msg", "整单作废成功");
            jsonObject.put("docStatus", "3");
            jsonObject.put("success", true);
            invoiceHead.setDocNum(docNum);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }

    /**
     * 分组作废
     *
     * @param docNum
     * @return
     */
    @ApiOperation(value = "分组作废", notes = "分组作废")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupNum", value = "分组编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/abolishOne")
    public Object abolishOne(@RequestParam("docNum") String docNum, @RequestParam("groupNum") String groupNum, @ApiIgnore InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(docNum) || StringUtils.isEmpty(groupNum)) {
            jsonObject.put("msg", "分组作废失败");
            jsonObject.put("docStatus", "2");
            jsonObject.put("success", false);
        } else {
            InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
            invoiceSplitLine.setDocNum(docNum);
            invoiceSplitLine.setGroupNum(groupNum);
            splitLineService.abolishOne(invoiceSplitLine);
            jsonObject.put("msg", "分组作废成功");
            jsonObject.put("docStatus", "3");
            jsonObject.put("success", true);
            invoiceHead.setDocNum(docNum);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }

    /**
     * 取消整单作废
     *
     * @param docNum
     * @return
     */
    @ApiOperation(value = "取消整单作废", notes = "取消整单作废")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/cancelAbolishAll")
    public Object cancelAbolishAll(@RequestParam("docNum") String docNum, @ApiIgnore InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(docNum)) {
            jsonObject.put("msg", "取消整单作废失败，单据编码不能为空");
            jsonObject.put("docStatus", "3");
            jsonObject.put("success", false);
        } else {
            InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
            invoiceSplitLine.setDocNum(docNum);
            splitLineService.cancelAbolishAll(invoiceSplitLine);
            jsonObject.put("msg", "取消整单作废成功");
            jsonObject.put("docStatus", "2");
            jsonObject.put("success", true);
            invoiceHead.setDocNum(docNum);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }

    /**
     * 取消分组作废
     *
     * @param docNum
     * @return
     */
    @ApiOperation(value = "取消分组作废", notes = "取消分组作废")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupNum", value = "分组编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/cancelAbolishOne")
    public Object cancelAbolishOne(@RequestParam("docNum") String docNum, @RequestParam("groupNum") String groupNum, @ApiIgnore InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(docNum) || StringUtils.isEmpty(groupNum)) {
            jsonObject.put("msg", "取消分组作废失败，单据编码不能为空");
            jsonObject.put("success", false);
        } else {
            InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
            invoiceSplitLine.setDocNum(docNum);
            invoiceSplitLine.setGroupNum(groupNum);
            splitLineService.cancelAbolishOne(invoiceSplitLine);
            jsonObject.put("docStatus", "2");
            jsonObject.put("msg", "取消分组作废成功");
            jsonObject.put("success", true);
            invoiceHead.setDocNum(docNum);
            jsonObject.put("data", service.queryByEntity(invoiceHead));
        }
        return jsonObject;
    }


    /**
     * 删除单据
     *
     * @param docNum
     * @return
     */
    @ApiOperation(value = "删除单据", notes = "删除单据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNum", value = "单据编号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/del")
    public Object del(@RequestParam("docNum") String docNum, @ApiIgnore InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(docNum)) {
            jsonObject.put("msg", "删除失败！单据编号为空");
            jsonObject.put("success", false);
        } else {

            invoiceHead.setDocNum(docNum);
            boolean re = service.del(invoiceHead);

            if (!re) {
                //throw new BusinessException("保存失败！");
                jsonObject.put("msg", "删除失败");
                jsonObject.put("success", false);
            } else {
                jsonObject.put("msg", "删除成功");
                jsonObject.put("success", true);
            }
        }
        return jsonObject;
    }


    /**
     * 删除原始单据
     *
     * @param docNums
     * @return
     */
    @ApiOperation(value = "删除原始单据", notes = "删除原始单据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docNums", value = "单据编号(多个以逗号隔开，每个id加单引号)", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/delOriginalByIds")
    public Object delByIds(@RequestParam("docNums") String docNums, @ApiIgnore InvoiceHead invoiceHead) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(docNums)) {
            jsonObject.put("msg", "删除失败！原始单据编号为空");
            jsonObject.put("success", false);
        } else {

            invoiceHead.setOriginalNos(docNums);
            Integer re = originalHeadService.delByIds(invoiceHead);

            jsonObject.put("msg", "删除成功");
            jsonObject.put("success", true);
        }
        return jsonObject;
    }

    /**
     * 插入原始数据
     */
//	@ApiOperation(value = "插入表头数据", notes = "插入表头数据")
//	@PostMapping(value = {"/insertHead"}, produces = {"application/json"})
//	@ResponseBody
//	@Transactional
//	public Object insertHead(@RequestBody OriginalHeadDto originalHeadDto){
//		//插入表头数据
//		this.manager.insert("original_head.create",(IPO) originalHeadDto);
//		log.info("接收到开票头信息：{}",JSONObject.toJSONString(originalHeadDto));
//		return "1";
//	}
    @ApiOperation(value = "插入表头数据", notes = "插入表头数据")
    @PostMapping(value = {"/insertHead"}, produces = {"application/json"})
    @ResponseBody
    @Transactional
    public Object insertHead(@RequestBody InvoiceHead invoiceHead) {
        //插入表头数据
    	System.out.println("开始插入数据");
        this.service.save(invoiceHead,invoiceHead.getDocStatus());
        this.manager.insert("original_head.create", (IPO) invoiceHead);
        
        log.info("接收到开票头信息：{}", JSONObject.toJSONString(invoiceHead));
        return "1";
    }

    @ApiOperation(value = "插入表体数据", notes = "插入表体数据")
    @PostMapping(value = {"/insertBody"}, produces = {"application/json"})
    @ResponseBody
    @Transactional
    public Object insertBody(@RequestBody OriginalBodyDto originalBodyDto) {
        //插入表体数据
        this.manager.insert("original_body.create", (IPO) originalBodyDto);
        log.info("接收到开票体信息：{}", JSONObject.toJSONString(originalBodyDto));
        return "1";
    }

    /**
     * 提交信息开票，需要补充客户详细信息以及其他开票所需要的参数
     *
     * @param invoiceHeadDto
     * @return
     */

    @ApiOperation(value = "提交开票", notes = "提交开票")
    @PostMapping(value = {"/invoice"}, produces = {"application/json"})
    @ResponseBody
    public Object invoice(@RequestBody InvoiceHeadDto invoiceHeadDto) {

//		invoiceHeadDto.setOrgName("百旺电子测试1");
//		invoiceHeadDto.setOrgTaxcode("110109500321654");
        log.info("接收到开票信息：{}", JSONObject.toJSONString(invoiceHeadDto));
        Object o = this.service.invoice(invoiceHeadDto);
        return o;
    }


    @GetMapping("/to_nc")
    public Object to_nc(@RequestParam("billid") String billid) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(billid)) {
            jsonObject.put("msg", "同步失败！单据编号为空");
            jsonObject.put("success", false);
        } else {
//			{
//				"billid":"16266222",
//					"action":"0",
//					"invoicecode":"233",
//					"invoicenumber":"123"
//			}
            JSONObject json = new JSONObject();
            json.put("billid", billid);
            json.put("action", "0");
            json.put("invoicecode", "3308388833");
            json.put("invoicenumber", "08387372");
            Boolean re = HttpPostUtil.post("data=" + json.toJSONString(), "http://125.210.100.94:80/servlet/WriteBackSettleListServlet");
            if (re) {
                jsonObject.put("msg", "同步到NC成功");
                jsonObject.put("success", true);
            } else {
                jsonObject.put("msg", "同步到NC失败");
                jsonObject.put("success", false);
            }

        }
        return jsonObject;
    }

    @GetMapping("/to_nc1")
    public Object to_nc1(@RequestParam("billid") String billid) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(billid)) {
            jsonObject.put("msg", "同步失败！单据编号为空");
            jsonObject.put("success", false);
        } else {

            JSONObject json = new JSONObject();
            json.put("billid", billid);
            json.put("action", "1");
            json.put("invoicecode", "");
            json.put("invoicenumber", "");
            Boolean re = HttpPostUtil.post("data=" + json.toJSONString(), "http://125.210.100.94:80/servlet/WriteBackSettleListServlet");
            if (re) {
                jsonObject.put("msg", "同步到NC成功");
                jsonObject.put("success", true);
            } else {
                jsonObject.put("msg", "同步到NC失败");
                jsonObject.put("success", false);
            }

        }
        return jsonObject;
    }


}