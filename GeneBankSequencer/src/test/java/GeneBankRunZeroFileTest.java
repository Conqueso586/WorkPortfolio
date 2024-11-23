import Main.GeneBankCreateBTree;
import Main.GeneBankSearch;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeneBankRunZeroFileTest {

    private final int testNum = 0;

    @BeforeEach
    void setUp() {
        System.out.println("Next Test");
        System.out.println("----------------------------------------------------------------------------\n");
    }

    @AfterEach
    void tearDown() {
        System.out.println("----------------------------------------------------------------------------");
    }

    @Test
    public  void testCreateAndSearchSeq1(){
        String seqLength = "1";
        String degree = "3";
        CreateAndSearchSetup(degree, seqLength);
    }

    @Test
    public  void testCreateAndSearchSeq2(){
        String seqLength = "2";
        String degree = "3";
        CreateAndSearchSetup(degree, seqLength);
    }

    @Test
    public  void testCreateAndSearchSeq3(){
        String seqLength = "3";
        String degree = "3";
        CreateAndSearchSetup(degree, seqLength);
    }

    @Test
    public  void testCreateAndSearchSeq4(){
        String seqLength = "4";
        String degree = "3";
        CreateAndSearchSetup(degree, seqLength);
    }

    @Test
    public  void testCreateAndSearchSeq5(){
        String seqLength = "5";
        String degree = "3";
        CreateAndSearchSetup(degree, seqLength);
    }

    private void CreateAndSearchSetup(String degree, String seqLength) {
        GeneBankCreateBTree GBC = GenerateBTree(new String[]{"0", degree,"test" + testNum + ".gbk", seqLength, "0"});
        GeneBankSearch GBS = GeneBankSearch(new String[]{"0", "test" + testNum + ".gbk.btree.data." + seqLength + "." + degree, "query" + seqLength, "0"});
    }

    private GeneBankCreateBTree GenerateBTree(String[] args) {
        return GeneBankCreateBTree.RunGeneBankCreateBTree(args);
    }

    private GeneBankSearch GeneBankSearch(String[] args){
        return GeneBankSearch.RunGeneBankSearch(args);
    }
}