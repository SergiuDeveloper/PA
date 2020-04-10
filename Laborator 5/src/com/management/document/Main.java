package com.management.document;

public class Main {
    public static void main(String[] args) {
        CatalogHandlerShell catalogHandlerShell = new CatalogHandlerShell();
        try {
            catalogHandlerShell.initialize();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
