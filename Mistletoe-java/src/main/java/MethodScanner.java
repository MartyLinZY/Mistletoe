import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.Modifier;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class MethodScanner {
    private void bookTest() throws FileNotFoundException{
        CompilationUnit cu = StaticJavaParser.parse(new File("src/main/resources/hw.java"));
        ClassOrInterfaceDeclaration book = cu.addClass("Book");

        book.addField("String", "title");
        book.addField("Person", "author");

        book.addConstructor(Modifier.Keyword.PUBLIC)
                .addParameter("String", "title")
                .addParameter("Person", "author")
                .setBody(new BlockStmt()
                        .addStatement(new ExpressionStmt(new AssignExpr(
                                new FieldAccessExpr(new ThisExpr(), "title"),
                                new NameExpr("title"),
                                AssignExpr.Operator.ASSIGN)))
                        .addStatement(new ExpressionStmt(new AssignExpr(
                                new FieldAccessExpr(new ThisExpr(), "author"),
                                new NameExpr("author"),
                                AssignExpr.Operator.ASSIGN))));

        book.addMethod("getTitle", Modifier.Keyword.PUBLIC).setBody(
                new BlockStmt().addStatement(new ReturnStmt(new NameExpr("title"))));

        book.addMethod("getAuthor", Modifier.Keyword.PUBLIC).setBody(
                new BlockStmt().addStatement(new ReturnStmt(new NameExpr("author"))));

        System.out.println(cu.toString());
    }
    private void hwTest() throws FileNotFoundException{
        // The directory where we store the examples
        // Parse the code of an entire source file, a.k.a. a Compilation Unit
        CompilationUnit cu = StaticJavaParser.parse(new File("src/main/resources/hw.java"));
        ClassOrInterfaceDeclaration hw = cu.getClassByName("hw").get();
        List<MethodDeclaration> x= hw.getMethods();
        for(MethodDeclaration md:x){
            md.getBody().get().addStatement(1,StaticJavaParser.parseStatement("Thread.sleep(1000);"));
            System.out.println(md);
        }
    }
    private void addLock(){
        ReentrantLock lock=new ReentrantLock();
        try {
            CompilationUnit cu = StaticJavaParser.parse(new File("src/main/resources/hw.java"));
            ClassOrInterfaceDeclaration hw = cu.getClassByName("hw").get();

            List<MethodDeclaration> x= hw.getMethods();
            String st1 = "ReentrantLock lock=new ReentrantLock();";
            String st2 = "lock.lock();" ;
            String st3 = "lock.unlock();//解锁";
            for(MethodDeclaration md:x){
                md.getBody().get()
                        .addStatement(0,StaticJavaParser.parseStatement(st1))
                        .addStatement(1,StaticJavaParser.parseStatement(st2))
                        .addStatement(2,StaticJavaParser.parseStatement(st3));
                System.out.println(md);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        lock.lock();
        lock.unlock();
    }
    public static void main(String[] args) throws FileNotFoundException {
        MethodScanner ms=new MethodScanner();
        ms.addLock();
    }

}

