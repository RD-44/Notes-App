package uk.ac.ucl.main;

import java.io.File;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {
  // This is for setting up the tomcat client
  public static void main(String[] args) throws Exception {
    // Features : Image from computer to store on server, bootstrap, Allow for same name in diff lists.
    // Might have to incorporate unique ids for every Item - or have a look at hash values.
    String webappDirLocation = "src/main/webapp/";
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();
    StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

    File additionWebInfClasses = new File("target/classes");

    WebResourceRoot resources = new StandardRoot(ctx);
    resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
      additionWebInfClasses.getAbsolutePath(), "/"));
    ctx.setResources(resources);

    tomcat.start();
    tomcat.getServer().await();
  }
}