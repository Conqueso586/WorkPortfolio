import Helpers.QueryFile;
import Main.GeneBankCreateBTree;
import Main.GeneBankSearch;
import org.junit.jupiter.api.Test;

class GeneBankInputTests {

    private void CreateInputTestTemplate(String[] args, String errorMessage) {
        try {
            GeneBankCreateBTree.RunGeneBankCreateBTree(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals(errorMessage);
        }
    }

    private void SearchInputTestTemplate(String[] args, String errorMessage) {
        try {
            GeneBankSearch.RunGeneBankSearch(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals(errorMessage);
        }
    }

    @Test
    public void testSearchEmptyArgs(){
        String[] arguments = new String[]{};
        SearchInputTestTemplate(arguments, GeneBankSearch.JAVA_GENE_BANK_SEARCH_INPUT_FORMAT);
    }

    @Test
    public void testSearchMismatchedQuerySequenceLength(){
        String[] arguments = new String[]{"0", "test0.gbk.btree.data.4.4", "query1", "0"};
        SearchInputTestTemplate(arguments, QueryFile.SEQUENCE_QUERY_LENGTH_MISMATCH);
    }

    @Test
    public void testSearchCacheInitError(){
        String[] arguments = new String[]{"-1", "test0.gbk.btree.data.4.4", "query4", "0"};
        SearchInputTestTemplate(arguments, GeneBankSearch.CACHE_INIT_ERROR);
    }

    @Test
    public void testSearchInvalidDebugLevel(){
        String[] arguments = new String[]{"0", "test0.gbk.btree.data.4.4", "query1", "4"};
        SearchInputTestTemplate(arguments, GeneBankSearch.INVALID_DEBUG_LEVEL + "4");
    }

    @Test
    public void testCreateFilenameInvalid(){
        String[] arguments = new String[]{"0", "4", "test0", "4", "0"};
        CreateInputTestTemplate(arguments, GeneBankCreateBTree.INVALID_FILENAME);
    }
}