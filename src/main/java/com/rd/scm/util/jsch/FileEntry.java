package com.rd.scm.util.jsch;

import java.io.Serializable;

public class FileEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String fileName;
    private String filePath;
    private String fileSize;
    
    public FileEntry() {
         
    }

    public FileEntry(String fileName, String fileSize, String filePath) {
        super();
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
     
    public String toString() {
        return fileName + ", " + fileSize + ", dir=" + filePath;
    }
}
