package com.dao.impl.jdbc.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Inner helper class to build complex select query
 */
public class ConditionSelectQueryBuilder {
    //TODO date param setting
    private final static String WHERE=" WHERE ";
    private final static String AND=" AND ";
    private final static String LIMIT=" LIMIT ? ";
    private final static String OFFSET=" OFFSET ? ";
    private Connection connection;
    /*Map to hold parameters data
        Integer - > order number in preparedStatement
        Object -> data to set
        */
    private HashMap<Integer,Object> params =new HashMap<>();

    /*initial where close*/
    private StringBuilder whereQuery=new StringBuilder();

    /*initial statement parameter index*/
    private Integer paramIndex=1;

    public ConditionSelectQueryBuilder(Connection connection){
        this.connection=connection;
    }

    public void addFilterParam(Object val,String whereClose){
        if(val!=null && !val.equals("")){
            //if at least one param was added
            if(paramIndex!=1){
                whereQuery.append(AND);
            }

            whereQuery.append(whereClose);
            params.put(paramIndex,val);
            paramIndex++;
        }
    }

    public PreparedStatement getPreparedStatement(final String SELECT_QUERY) throws SQLException {
        return getPreparedStatementLimitOffset(SELECT_QUERY,null,null);
    }

    public PreparedStatement getPreparedStatementLimit(final String SELECT_QUERY,Integer limit) throws SQLException {
        return getPreparedStatementLimitOffset(SELECT_QUERY,limit,null);
    }

    public PreparedStatement getPreparedStatementOffset(final String SELECT_QUERY,Integer offset) throws SQLException {
        return getPreparedStatementLimitOffset(SELECT_QUERY,null,offset);
    }

    public PreparedStatement getPreparedStatementLimitOffset(final String SELECT_QUERY,Integer limit,Integer offset) throws SQLException {
        //if at least one param was added
        if(paramIndex!=1){
            //prepend
            whereQuery.insert(0,WHERE);
        }

        String RESULT_QUERY= SELECT_QUERY + whereQuery.toString();

        /*check if params passed*/
        boolean isLimit = limit!=null;
        boolean isOffset = offset!=null;

        /*order of checks is necessary*/
        if(isLimit){
            RESULT_QUERY+=LIMIT;
        }

        if(isOffset){
            RESULT_QUERY+=OFFSET;
        }

        /*result preparedStatement*/
        PreparedStatement resultStatement=connection.prepareStatement(RESULT_QUERY);

        /*set param values*/
        for(Map.Entry<Integer,Object> entry:params.entrySet()){

            Integer index=entry.getKey();
            Object param=entry.getValue();

            //check param for type
            if(param instanceof Date){
                resultStatement.setDate(index,new java.sql.Date(((Date)param).getTime()));
                continue;
            }

            if(param instanceof String || param.getClass().isEnum()){
                resultStatement.setString(index,param.toString());
                continue;
            }
            //integer type left
            resultStatement.setInt(index,(Integer)param);
        }

        /*!order of checks is necessary*/
        /*set limit*/
        if(isLimit){
            resultStatement.setInt(paramIndex++, limit);
        }
        /*set offset*/
        if(isOffset){
            resultStatement.setInt(paramIndex, offset);
        }

        return  resultStatement;
    }
}
