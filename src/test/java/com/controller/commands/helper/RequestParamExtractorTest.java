package com.controller.commands.helper;

import com.controller.exception.ControllerException;
import com.model.entity.book.BookGenre;
import com.model.entity.order.OrderType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Test for RequestParamExtractor
 * Created by vlad on 19.04.17.
 */
public class RequestParamExtractorTest {


    private Logger logger=Logger.getLogger(RequestParamExtractorTest.class.getName());
    private  RequestParamExtractor extractor;

    @Mock
    private HttpServletRequest mockedRequest;

    private final String INT_PARAM_NAME="intParam";
    private final String STRING_PARAM_NAME="stringParam";
    private final String DATE_PARAM_NAME="dateParam";
    private final String NULL_PARAM_NAME="nullParam";
    private final String ENUM_PARAM_NAME ="enumParam";


    @Before
    public void init(){
        extractor=new RequestParamExtractor();

        /*init mock annotations*/
        MockitoAnnotations.initMocks(this);

        when(mockedRequest.getParameter(INT_PARAM_NAME)).thenReturn("12");
        when(mockedRequest.getParameter(STRING_PARAM_NAME)).thenReturn("some string");
        when(mockedRequest.getParameter(DATE_PARAM_NAME)).thenReturn("2017-04-03");
        when(mockedRequest.getParameter(ENUM_PARAM_NAME)).thenReturn("KIDS");
        when(mockedRequest.getParameter(NULL_PARAM_NAME)).thenReturn(null);
    }

    @Test
    public void getEnumParamOrNull() throws Exception {
        BookGenre genre=extractor.getEnumParamOrNull(mockedRequest,ENUM_PARAM_NAME,BookGenre.class);
        logger.info("enum value is "+genre);
        assertNotNull(genre);
    }

    @Test(expected = ControllerException.class)
    public void getEnumParamOrNullException() throws Exception {
        logger.info("before exception");
        OrderType genre=extractor.getEnumParamOrNull(mockedRequest,ENUM_PARAM_NAME,OrderType.class);
        assertNull(genre);
    }

    @Test
    public void getEnumParamOrNullNullExpected() throws Exception {
        OrderType genre=extractor.getEnumParamOrNull(mockedRequest,NULL_PARAM_NAME,OrderType.class);
        logger.info("enum value is "+genre);
        assertNull(genre);
    }


    @Test
    public void getStringParamOrNull() throws Exception {
        String result=extractor.getStringParamOrNull(mockedRequest,STRING_PARAM_NAME);
        logger.info(result);
        assertNotNull(result);
    }

    @Test
    public void getDateParamOrNull() throws Exception {
        Date result=extractor.getDateParamOrNull(mockedRequest,DATE_PARAM_NAME);
        logger.info(result.toString());
        assertNotNull(result);
    }


    @Test(expected = ControllerException.class)
    public void getIntParam() throws Exception {
        logger.info("before exception");
        Integer result=extractor.getIntParamOrNull(mockedRequest,STRING_PARAM_NAME);
        assertNotNull(result);
    }

    @Test(expected = ControllerException.class)
    public void getStringParam() throws Exception {
        logger.info("before exception");
        String result=extractor.getStringParam(mockedRequest,NULL_PARAM_NAME);
        assertNotNull(result);
    }

    @Test(expected = ControllerException.class)
    public void getDateParam() throws Exception {
        logger.info("before exception");
        Date result=extractor.getDateParam(mockedRequest,NULL_PARAM_NAME);
        assertNotNull(result);
    }

}