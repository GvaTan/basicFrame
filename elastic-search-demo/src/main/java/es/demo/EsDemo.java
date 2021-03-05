package es.demo;

import common.User;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EsDemo {
    private TransportClient client = null;

    public void getConnection() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch-application")
                .build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(
                        new TransportAddress(
                                InetAddress.getByName("192.168.9.151"), 8301));
    }

    public void addIndex() throws IOException {
        // 创建一个默认的index
        client.admin().indices().prepareCreate("sc_31").get();
        // 设置索引type
        XContentBuilder content = XContentFactory.jsonBuilder()
                .startObject()// {
                    .startObject("properties")// "properties" : {
                        .startObject("name")        // "name": {
                            .field("type", "text")// "type" : "text",
                            .field("analyzer", "ik_max_word")// "analyzer" : "ik_max_word"
                        .endObject()    // }
                        .startObject("age")
                            .field("type", "integer")
                        .endObject()
                        .startObject("sex")
                            .field("type", "integer")
                        .endObject()
                        .startObject("address")        // "address": {
                            .field("type", "text")// "type" : "text",
                            .field("analyzer", "ik_max_word")// "analyzer" : "ik_max_word"
                        .endObject()
                    .endObject() // }
                .endObject();// }
        client.admin().indices().preparePutMapping("sc_31")
                                .setType("user")
                                //.setSource("{\"properties\":{\"name\":{\"type\":\"text\",}}}", XContentType.JSON)
                                .setSource(content)
                                .get();

        // 关闭
        client.close();
    }

    public List<User> search() {// xx.search().size()
        SearchRequestBuilder builder = client.prepareSearch("sc_31", "sc_10");
        builder.setSize(10).setFrom(0);
        // 匹配度查询
        //builder.setQuery(QueryBuilders.matchQuery("name", "四"));
        // 过滤查询
        // term
        builder.setQuery(QueryBuilders.termQuery("age", 30));
        // range
                            // QueryBuilders.rangeQuery()
        // bool
        // must
                            // QueryBuilders.boolQuery().must()
        SearchResponse searchResponse = builder.execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        List<User> result = null;
        if (hits != null && hits.length != 0) {
            result = new ArrayList<User>();
            for (SearchHit hit : hits) {
                // fastJSON JSON.parseObject
                System.out.println(hit.getSourceAsString());
            }
        }
        return result;
    }

    public void addDocument() throws IOException {
        XContentBuilder content = XContentFactory.jsonBuilder()
                .startObject()
                    .field("name", "张三")
                    .field("age", 20)
                    .field("sex", 0)
                    .field("address", "北京市海淀区成府路")
                .endObject();
        client.prepareIndex("sc_31", "user", "01")// POST sc_31/user/
            .setSource(content)// {
            .get();// }

        client.close();
    }


    public static void main(String[] args) {
        EsDemo demo = new EsDemo();
        try {
            demo.getConnection();
            // demo.addIndex();
            // demo.addDocument();
            demo.search();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finish");
    }

}
