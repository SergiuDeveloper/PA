package com.management.document;

import java.util.List;
import java.util.Scanner;

public class CatalogHandlerShell {
    private abstract static class CatalogHandlerShellCommand {
        public CatalogHandlerShellCommand(CatalogHandler catalogHandler, CatalogHandlerShell catalogHandlerShell, String command, int parametersCount) {
            this.catalogHandler = catalogHandler;
            this.catalogHandlerShell = catalogHandlerShell;
            this.command = command;
            this.parametersCount = parametersCount;
        }

        protected CatalogHandler catalogHandler;
        protected CatalogHandler getCatalogHandler() {
            return this.catalogHandler;
        }
        protected void setCatalogHandler(CatalogHandler catalogHandler) {
            this.catalogHandler = catalogHandler;
        }

        protected CatalogHandlerShell catalogHandlerShell;
        protected CatalogHandlerShell getCatalogHandlerShell() {
            return this.catalogHandlerShell;
        }
        protected void setCatalogHandlerShell(CatalogHandlerShell catalogHandlerShell) {
            this.catalogHandlerShell = catalogHandlerShell;
        }

        protected String command;
        public String getCommand() {
            return this.command;
        }
        protected void setCommand(String command) {
            this.command = command;
        }

        protected int parametersCount;
        public int getParametersCount() {
            return this.parametersCount;
        }
        protected void setParametersCount(int parametersCount) {
            this.parametersCount = parametersCount;
        }

        public abstract void behaviour(List<String> parameters) throws Exception;
    }

    private static class ExitCommand extends CatalogHandlerShellCommand {
        public ExitCommand(CatalogHandlerShell catalogHandlerShell) {
            super(null, catalogHandlerShell, "exit", 0);
        }

        @Override
        public void behaviour(List<String> parameters) throws Exception {
            this.catalogHandlerShell.deinitialize();
        }
    }

    private static class SaveCommand extends CatalogHandlerShellCommand {
        public SaveCommand(CatalogHandler catalogHandler, CatalogHandlerShell catalogHandlerShell) {
            super(catalogHandler, catalogHandlerShell, "save", 1);
        }

        @Override
        public void behaviour(List<String> parameters) throws Exception {
            if (parameters.size() < this.parametersCount)
                throw new Exception("No path was provided to the save command");
            this.catalogHandler.save(parameters.get(0));
        }
    }

    private static class LoadCommand extends CatalogHandlerShellCommand {
        public LoadCommand(CatalogHandler catalogHandler, CatalogHandlerShell catalogHandlerShell) {
            super(catalogHandler, catalogHandlerShell, "load", 1);
        }

        @Override
        public void behaviour(List<String> parameters) throws Exception {
            if (parameters.size() < this.parametersCount)
                throw new Exception("No path was provided to the load command");
            this.catalogHandler.load(parameters.get(0));
        }
    }

    private static class ListCommand extends CatalogHandlerShellCommand {
        public ListCommand(CatalogHandler catalogHandler, CatalogHandlerShell catalogHandlerShell) {
            super(catalogHandler, catalogHandlerShell, "list", 0);
        }

        @Override
        public void behaviour(List<String> parameters) throws Exception {
            System.out.println("Documents currently contained in the catalog:");
            for (var document : this.catalogHandler.getCatalog().getDocumentsList())
                System.out.println(document.getName() + "-" + document.getPath());
        }
    }

    private static class ViewCommand extends CatalogHandlerShellCommand {
        public ViewCommand(CatalogHandler catalogHandler, CatalogHandlerShell catalogHandlerShell) {
            super(catalogHandler, catalogHandlerShell, "view", 1);
        }

        @Override
        public void behaviour(List<String> parameters) throws Exception {
            if (parameters.size() < this.parametersCount)
                throw new Exception("No path was provided to the view command");
            this.catalogHandler.view(parameters.get(0));
        }
    }

    public CatalogHandlerShell() {
        this.isActive = false;
        this.exitCommand = new ExitCommand(this);
        this.catalogHandlerShellCommandsList = List.of(
                this.exitCommand,
                new SaveCommand(this.catalogHandler, this),
                new LoadCommand(this.catalogHandler, this),
                new ListCommand(this.catalogHandler, this),
                new ViewCommand(this.catalogHandler, this)
        );
    }

    private CatalogHandler catalogHandler;
    public CatalogHandler getCatalogHandler() {
        return this.catalogHandler;
    }
    private void setCatalogHandler(CatalogHandler catalogHandler) {
        this.catalogHandler = catalogHandler;
    }

    private Scanner scanner;
    private Scanner getScanner() {
        return this.scanner;
    }
    private void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private boolean isActive;
    private boolean getIsActive() {
        return this.isActive;
    }
    private void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    private List<CatalogHandlerShellCommand> catalogHandlerShellCommandsList;
    private List<CatalogHandlerShellCommand> getCatalogHandlerShellCommandsList() {
        return this.catalogHandlerShellCommandsList;
    }
    private void setCatalogHandlerShellCommandsList(List<CatalogHandlerShellCommand> catalogHandlerShellCommandsList) {
        this.catalogHandlerShellCommandsList = catalogHandlerShellCommandsList;
    }

    private ExitCommand exitCommand;
    private ExitCommand getExitCommand() {
        return this.exitCommand;
    }
    private void setExitCommand(ExitCommand exitCommand) {
        this.exitCommand = exitCommand;
    }

    public void initialize() throws Exception {
        if (this.isActive)
            throw new Exception("This shell is already active");

        this.scanner = new Scanner(System.in);
        this.catalogHandler = new CatalogHandler();

        this.isActive = true;

        String command;
        while (this.isActive) {
            System.out.print("Catalog Handler Shell: ");
            command = this.scanner.nextLine();
            try {
                this.parseCommand(command);
            }
            catch (CommandNotFoundException exception) {
                System.out.println("Command not found");
            }
        }
    }

    private void deinitialize() throws Exception {
        if (!this.isActive)
            throw new Exception("This shell is already inactive");

        this.scanner.close();

        this.isActive = false;
    }

    private void parseCommand(String command) throws Exception {
        if (command.startsWith(this.exitCommand.getCommand())) {
            this.exitCommand.behaviour(null);
            return;
        }

        int firstSpace = command.indexOf(' ');
        String request;
        String parameter = null;
        if (firstSpace == -1)
            request = command;
        else {
            request = command.substring(0, firstSpace);
            parameter = command.substring(firstSpace + 1);
        }

        for (var shellCommand : this.catalogHandlerShellCommandsList)
            if (shellCommand.getCommand().equals(request)) {
                shellCommand.behaviour(parameter == null ? List.of() : List.of(parameter));
                return;
            }

        throw new CommandNotFoundException("Command not found");
    }
}
