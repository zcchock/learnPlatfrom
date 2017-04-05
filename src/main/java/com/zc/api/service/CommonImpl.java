package com.zc.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by chock on 2016/10/19.
 */
@Configuration
public class CommonImpl {

    private static final JsonMapper mapper = new JsonMapper();

    /**
     * 数据处理，map TO Obj
     *
     * @param dataRequest
     * @param response
     * @param key         js中的数据名
     * @param cls         map转成的对象类型
     * @param logCls      日志对应Class
     * @return
     */
    public Object mapJsonToObj(DataRequest dataRequest, DataResponse response, String key, Class cls, Class logCls) {
        Object obj = null;
        Logger LOGGER = LoggerFactory.getLogger(logCls);
        try {
            JsonNode jsonNode = mapper.readTree(mapper.toJson(dataRequest.getData()));
            obj = mapper.treeToValue(jsonNode.get(key), cls);
        } catch (JsonProcessingException e) {
            response.setMessage("JSON处理错误");
            LOGGER.error("JSON处理错误");
        } catch (IOException e) {
            response.setMessage("IO错误");
            LOGGER.error("IO错误");
        }
        return obj;
    }

    /**
     * 操作后的response封装
     *
     * @param response
     * @param status
     * @param data
     * @param message
     * @return
     */
    public DataResponse responseDeal(DataResponse response, String status, Object data, String message) {
        response.setStatus(status);
        response.setData(mapper.toJson(data));
        response.setMessage(message);
        return response;
    }

    /**
     * 多表查询时，设置参数，构造DataTablesOutput
     *
     * @param data
     * @param keys
     * @param input
     * @return
     */
  /*  public DataTablesOutput getDataTableOutput(List<Object> data, String[] keys, DataTablesInput input) {
        List<Map<String, Object>> resultList = ObjectToMap.objectChangeMap(data, keys);
        DataTablesOutput<Map<String, Object>> mchntCertTablesOutput = new DataTablesOutput<>();
        mchntCertTablesOutput.setDraw(input.getDraw());
        mchntCertTablesOutput.setData(resultList);
        mchntCertTablesOutput.setRecordsFiltered(resultList.size());
        mchntCertTablesOutput.setRecordsTotal(resultList.size());
        return mchntCertTablesOutput;
    }*/


    //获取usrId,当设置创建人，最后更新操作人员时时
    public String getUserId(DataRequest dataRequest) {
       return "";
    }
    public String getLoginType() {
       return "";
    }



    /**
     * 调试日志
     *
     * @param logCls 调用类名称
     * @param Msg    描述内容
     */
    public void getDebugLog(Class logCls, String Msg) {

        Logger LOGGER = LoggerFactory.getLogger(logCls);
        LOGGER.debug("当前服务：" + Msg);

    }



    /**
     * 数据处理，map TO Obj
     *
     * @param dataRequest
     * @param response
     * @param cls         map转成的对象类型
     * @param logCls      日志对应Class
     * @return
     */
    public Object mapJsonToObj(DataRequest dataRequest, DataResponse response, Class cls, Class logCls) {
        Object obj = null;
        Logger LOGGER = LoggerFactory.getLogger(logCls);
        try {
            JsonNode jsonNode = mapper.readTree(mapper.toJson(dataRequest.getData()));
            obj = mapper.treeToValue(jsonNode, cls);
        } catch (JsonProcessingException e) {
            response.setMessage("JSON处理错误");
            LOGGER.error("JSON处理错误");
        } catch (IOException e) {
            response.setMessage("IO错误");
            LOGGER.error("IO错误");
        }
        return obj;
    }


}