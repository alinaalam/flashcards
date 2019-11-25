package flashcards.infra.cmd;

public class ParamsParser {

    private static final String IMPORT = "-import";
    private static final String EXPORT = "-export";

    private boolean shouldImport;
    private boolean shouldExport;
    private String fileToImport;
    private String fileToExportTo;

    private Params params;

    public ParamsParser(String[] args) {
        parseImportFlag(args);
        parseExportFlag(args);
        params = new Params(shouldImport, shouldExport, fileToImport, fileToExportTo);
    }

    public Params getParams() {
        return params;
    }

    private void parseImportFlag(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(IMPORT)) {
                shouldImport = true;
                fileToImport = args[i + 1];
                return;
            }
        }
        shouldImport = false;
    }

    private void parseExportFlag(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(EXPORT)) {
                shouldExport = true;
                fileToExportTo = args[i + 1];
                return;
            }
        }
        shouldExport = false;
    }
}
