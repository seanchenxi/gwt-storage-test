package com.seanchenxi.gwt.storage.test.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.seanchenxi.gwt.storage.shared.StorageUtils;
import com.seanchenxi.gwt.storage.test.client.GreetingService;
import com.seanchenxi.gwt.storage.test.shared.FieldVerifier;
import com.seanchenxi.gwt.storage.test.shared.TestValue;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Random;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    StorageUtils.loadSerializationPolicy(config.getServletContext(), "test");
  }

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid. 
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    TestValue value = new TestValue();
    value.setAge(new Random().nextInt());
    value.setName("Hello, " + input + "!<br><br>I am running " + serverInfo
            + ".<br><br>It looks like you are using:<br>" + userAgent);
    try {
      return StorageUtils.serialize(value);
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @Override
  public TestValue testValue() {
    return new TestValue();
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }

  public static SerializationPolicy loadSerializationPolicy(ServletContext context, String moduleName) {
    if(true) {
      String contextRelativePath = "/" + moduleName + "/" + "StorageSerializerPolicy";
      context.log(contextRelativePath);
      String serializationPolicyFilePath = SerializationPolicyLoader.getSerializationPolicyFileName(contextRelativePath);
      context.log(serializationPolicyFilePath);
      InputStream is = context.getResourceAsStream(serializationPolicyFilePath);

      try {
        if(is != null) {
          try {
            return loadSerializationPolicy(is, moduleName, true);
          } catch (ParseException var18) {
            context.log("ERROR: Failed to parse the policy file \'" + serializationPolicyFilePath + "\'", var18);
          } catch (IOException var19) {
            context.log("ERROR: Could not read the policy file \'" + serializationPolicyFilePath + "\'", var19);
          }
        } else {
          String e = "ERROR: The serialization policy file \'" + serializationPolicyFilePath + "\' was not found; did you forget to include it in this deployment?";
          context.log(e);
        }
      } finally {
        if(is != null) {
          try {
            is.close();
          } catch (IOException var17) {
          }
        }

      }

    }
    return null;
  }

  public static SerializationPolicy loadSerializationPolicy(InputStream is, String moduleName, boolean isDefault) throws IOException, ParseException {
    return SerializationPolicyLoader.loadFromStream(is, null);
  }
}
