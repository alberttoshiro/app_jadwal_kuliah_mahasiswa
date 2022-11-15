package com.albert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.albert.processdata.FolderListener;

@Path("/app")
public class App {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String testApp() {
    FolderListener folderListener = new FolderListener();
    Thread thread = new Thread(folderListener);
    thread.start();
    return "Read and process file data";
  }

}
