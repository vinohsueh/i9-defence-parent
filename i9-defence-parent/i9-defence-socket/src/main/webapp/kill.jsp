<%@page import="java.lang.reflect.Field"%>
<%@page import="i9.defence.platform.socket.context.ChannelPacker"%>
<%@page import="i9.defence.platform.socket.context.ChannelPackerServerContext"%>
<%@page import="i9.defence.platform.socket.util.ChannelConnectedService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
ChannelPackerServerContext context = applicationContext.getBean(ChannelPackerServerContext.class);
                                                      //000000000001001000FFD5
// ChannelPacker channelPacker = context.getChannelPacker("000000000001000FFD5");
Field field = context.getClass().getDeclaredField("context");
field.setAccessible(true);
out.print(field.get(context));
%>