package com.view.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by vlad on 17.04.17.
 */
public class PaginationTagHandler extends SimpleTagSupport {
    private static final Logger logger = Logger.getLogger(PaginationTagHandler.class);
    private final int FIRST_PAGE_NUMBER=1;

    private String uri;
    private String paramName;
    private int currPage;
    private int totalPages;
    /*default value*/
    private int maxLinks=10;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        boolean lastPage = currPage == totalPages;
        int pgStart = Math.max(currPage - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;
        if (pgEnd > totalPages + 1) {
            int diff = pgEnd - totalPages;
            pgStart -= diff - 1;
            if (pgStart < 1)
                pgStart = 1;
            pgEnd = totalPages + 1;
        }

        try {
            out.write("<ul class=\"pagination\">");

            if (currPage > 1)
                out.write(constructLink(currPage - 1, "&laquo;", "paginatorPrev"));

            for (int i = pgStart; i < pgEnd; i++) {
                if (i == currPage)
                    out.write("<li class=\"active"+ (lastPage && i == totalPages ? " paginatorLast" : "")  +"\">"+
                            "<a href=\"#\"/>"+currPage
                            + "</a></li>");
                else
                    out.write(constructLink(i));
            }

            if (!lastPage)
                out.write(constructLink(currPage + 1, "&raquo;", "paginatorNext paginatorLast"));

            out.write("</ul>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }

    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }

    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        link.append(">")
                .append("<a href=\"")
                .append(uri.replace("##", String.valueOf(page)))
                .append("\">")
                .append(text)
                .append("</a></li>");
        return link.toString();
    }
}
