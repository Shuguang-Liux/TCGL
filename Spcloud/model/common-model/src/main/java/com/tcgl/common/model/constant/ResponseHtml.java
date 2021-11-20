package com.tcgl.common.model.constant;

public class ResponseHtml {

    public static String orderExportNotFoundHtml(String description, String imageFileContent){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>\n<body>\n\t<img src=\"");
        sb.append(imageFileContent);
        sb.append("\" style=\"display: block;text-align: center;width:200px;margin: 100px auto 30px\">\n");
        sb.append("\t<p style=\"color: #424e67;margin: 20px 0;font-weight: 200;text-align: center\">");
        sb.append(description);
        sb.append("</p>\n");
        sb.append("</body>\n");
        sb.append("</html>");
        return sb.toString();
    }
}
