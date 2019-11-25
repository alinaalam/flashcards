package flashcards.infra.cmd;

public class Params {

    private boolean shouldImport;
    private boolean shouldExport;
    private String fileToImport;
    private String fileToExportTo;

    public Params(boolean shouldImport, boolean shouldExport, String fileToImport, String fileToExportTo) {
        this.shouldImport = shouldImport;
        this.shouldExport = shouldExport;
        this.fileToImport = fileToImport;
        this.fileToExportTo = fileToExportTo;
    }

    public boolean shouldImport() {
        return shouldImport;
    }

    public boolean shouldExport() {
        return shouldExport;
    }

    public String getFileToImport() {
        return fileToImport;
    }

    public String getFileToExportTo() {
        return fileToExportTo;
    }
}
