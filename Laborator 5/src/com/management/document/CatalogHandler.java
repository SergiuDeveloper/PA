package com.management.document;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogHandler {
    public static class Catalog implements Serializable {
        private Catalog() {
            this.documentsList = new ArrayList<>();
        }

        private List<Document> documentsList;
        public List<Document> getDocumentsList() {
            return this.documentsList;
        }
        private void setDocumentsList(List<Document> documentsList) {
            this.documentsList = documentsList;
        }
    }

    private Catalog catalog;
    public Catalog getCatalog() {
        return this.catalog;
    }
    private void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean save(String path) throws Exception {
        if (this.catalog == null)
            throw new Exception("The catalog handler attempted to save a catalog without loading it first");

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(path);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this.catalog);

            objectOutputStream.flush();
        }
        catch (Exception exception) {
            System.out.println("Could not save catalog on specified path");
            return false;
        }

        try {
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception exception) {
            System.out.println("Could not properly close the output stream when trying to save a catalog");
        }

        return true;
    }

    public boolean load(String path) {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.catalog = (Catalog)objectInputStream.readObject();
        }
        catch (Exception exception) {
            System.out.println("Could not load catalog from specified path");
            return false;
        }

        try {
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (Exception exception) {
            System.out.println("Could not properly close the input stream when trying to save a catalog");
        }

        return true;
    }

    public boolean view(Document document) throws Exception {
        return this.view(document.getPath());
    }

    public boolean view(String documentPath) throws Exception {
        if (this.catalog == null)
            throw new Exception("The catalog handler attempted to view a catalog without loading it first");

        Document documentToView = null;
        for (var catalogDocument : this.catalog.documentsList)
            if (catalogDocument.getPath().equals(documentPath))
                documentToView = catalogDocument;
        if (documentToView == null)
            throw new Exception("The document could not be found in the catalog");

        if (!Desktop.isDesktopSupported())
            throw new Exception("This machine does not have a desktop");
        try {
            Desktop desktop = Desktop.getDesktop();
            File documentFile = new File(documentToView.getPath());
            desktop.open(documentFile);
        }
        catch (Exception exception) {
            System.out.println("Could not view the requested document");
            return false;
        }

        return true;
    }
}
