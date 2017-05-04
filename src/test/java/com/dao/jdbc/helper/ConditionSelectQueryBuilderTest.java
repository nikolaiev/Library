package com.dao.jdbc.helper;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by vlad on 18.04.17.
 */
public class ConditionSelectQueryBuilderTest {

    @Test
    public void getPreparedStatement() throws Exception {
        Connection mockConnection=mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        /*inject PrepareStatement mock*/
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        ConditionSelectQueryBuilder queryBuilder=new ConditionSelectQueryBuilder(mockConnection);

        Integer testObject= 1;
        String testQueryClose="testField = ?";
        String selectClose="SELECT * from testTable";


        queryBuilder.addFilterParam(testObject,testQueryClose);
        queryBuilder.addFilterParam(testObject,testQueryClose);
        //exception here
        queryBuilder.getPreparedStatement(selectClose);

        int expectedPrepStatTimes=1;
        int expectedSetValTimes=2;
        verify(mockConnection,times(expectedPrepStatTimes)).prepareStatement(anyString());
        verify(mockStatement,times(expectedSetValTimes)).setInt(anyInt(),anyInt());
    }

    @Test(expected = ClassCastException.class)
    public void getPreparedStatementException() throws Exception{
        Connection mockConnection=mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        /*inject PrepareStatement mock*/
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        ConditionSelectQueryBuilder queryBuilder=new ConditionSelectQueryBuilder(mockConnection);

        Object testObject=new Object();
        String testQueryClose="testField = ?";
        String selectClose="SELECT * from testTable";


        queryBuilder.addFilterParam(testObject,testQueryClose);
        //exception here
        queryBuilder.getPreparedStatement(selectClose);
    }
}