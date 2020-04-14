package com.system.service;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
	public final void log(String level, String message, String className) throws SQLException, IOException{
		Logger logger = Logger.getLogger(className);
		switch(level) {
		case "debug": logger.debug(message);break;
		case "info": logger.info(message);break;
		case "warn": logger.warn(message);break;
		case "fatal": logger.fatal(message);break;
		case "error": logger.error(message);break;
		case "trace": logger.trace(message);break;
		} 
	}
}
