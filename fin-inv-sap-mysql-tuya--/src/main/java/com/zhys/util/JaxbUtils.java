package com.zhys.util;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.text.MessageFormat;

/**
 * @Description:xml字符串转换Javabean
 * @author:lgh
 * @date: 2018-11-20 10:55
 */

public class JaxbUtils {

    @SuppressWarnings("unchecked")
    private static <T> T readString(Class<T> clazz, String context)
            throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(new File(context));
        } catch (JAXBException e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T readConfig(Class<T> clazz, String config,
                                    Object... arguments) throws IOException, JAXBException {
        InputStream is = null;
        try {
            if (arguments.length > 0) {
                config = MessageFormat.format(config, arguments);
            }
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            is = new FileInputStream(config);
            return (T) u.unmarshal(is);
        } catch (IOException e) {
            throw e;
        } catch (JAXBException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T readConfigFromStream(Class<T> clazz,
                                              InputStream dataStream) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(dataStream);
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T readConfigFromRead(Class<T> clazz, Reader reader)throws JAXBException
    {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(reader);
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }
    /**
     * xml转换成java bean
     * @param xml
     * @param clazz
     * @return
     * @throws JAXBException
     */
    public static <T> T jaxbConvertXmlToBean(String xml,Class<T> clazz) {
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(xml.getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        T t = null;
        try {
            t = JaxbUtils.readConfigFromStream(clazz, is);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return t;
    }
    /**
     * java bean 转换成xml
     * @param object
     * @return
     * @throws JAXBException
     */
    public static String  jaxbConvertBeanToXml(Object object)throws JAXBException {
        StringWriter writer = new StringWriter();
        String returnString ="";
        try {
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(object, writer);
            returnString=writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  returnString;
    }
    public static String jaxbBeanToxml(Object object){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(baos, (String) jaxbMarshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) jaxbMarshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
            jaxbMarshaller.marshal(object, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            return new String(baos.toByteArray(),"UTF-8");

        } catch (Exception e) {
        }
        return null;


    }
    public static String jaxbBeanToxmlGBK(Object object){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(baos, (String) jaxbMarshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) jaxbMarshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
            jaxbMarshaller.marshal(object, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            return new String(baos.toByteArray(),"GBK");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
    /**
     * xml转换成java bean
     * @param xml
     * @param clazz
     * @return
     * @throws JAXBException
     */
    public static <T> T jaxbConvertXmlToBeanByGbk(String xml,Class<T> clazz) {
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(xml.getBytes("GBK"));

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        T t = null;
        try {
            t = JaxbUtils.readConfigFromStream(clazz, is);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  t;
    }



}

