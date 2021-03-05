package com.zhys.po;

import com.zhys.excel.ExcelCell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressExcel {

    @ExcelCell(name="订单号")
    public String exid;

    @ExcelCell(name="快递单号")
    public String exnum;

//    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<>();
//        Class clazz = com.zhys.po.ExpressExcel.class;
//        Field[] fs = clazz.getDeclaredFields();
//        if(fs != null&&fs.length>0) {
//            for(Field f:fs) {
//                ExcelCell ec = f.getAnnotation(ExcelCell.class);
//                if(ec!=null) {
//                    map.put(ec.name(), f.getName());
//                }
//
//            }
//            System.out.println("jieshu");
//        }
//    }
}
