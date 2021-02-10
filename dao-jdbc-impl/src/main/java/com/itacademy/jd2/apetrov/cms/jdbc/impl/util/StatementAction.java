package com.itacademy.jd2.apetrov.cms.jdbc.impl.util;

import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface StatementAction<RETURN_TYPE> {

    RETURN_TYPE doWithStatement(Statement stmt) throws SQLException;


}
