import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.Modifier;
import op.Sleep;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Random.*;


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
    private void scanTest() throws FileNotFoundException{
        // The directory where we store the examples
        // Parse the code of an entire source file, a.k.a. a Compilation Unit
        CompilationUnit cu = StaticJavaParser.parse(new File("src/main/resources/hw.java"));
        List<ClassOrInterfaceDeclaration> className= new ArrayList<ClassOrInterfaceDeclaration>();
        className= cu.findAll(ClassOrInterfaceDeclaration.class);
        for(ClassOrInterfaceDeclaration cd:className){
            System.out.println(cd.getName());
            ClassOrInterfaceDeclaration hw = cu.getClassByName(cd.getName().toString()).get();
            List<MethodDeclaration> x= hw.getMethods();
            for(MethodDeclaration md:x){
                System.out.println("\t"+md.getName());
            }
        }

    }
    private String randomString(int n){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random1=new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < n; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number=random1.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        return sb.toString();
    }
    public static List<String> getFilePath(String folderPath) {
        File folder = new File(folderPath);
        List<String> filePathList = new ArrayList<>();
        String rootPath;
        if (folder.exists()) {
            String[] fileNameList = folder.list();
            if (null != fileNameList && fileNameList.length > 0) {
                if (folder.getPath().endsWith(File.separator)) {
                    rootPath = folder.getPath();
                } else {
                    rootPath = folder.getPath() + File.separator;
                }
                for (String fileName : fileNameList) {
                    filePathList.add(rootPath + fileName);
                }
            }
        }
        return filePathList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String> strList= getFilePath("C:\\Users\\kinulin\\Downloads\\elasticsearch-8.2.2\\server\\src\\main\\java\\org\\elasticsearch\\http");
        for(String i :strList){
            System.out.println(i);
        }
        String filename="C:\\Users\\kinulin\\Downloads\\elasticsearch-8.2.2\\server\\src\\main\\java\\org\\elasticsearch\\http\\HttpClientStatsTracker.java";
        MethodScanner ms=new MethodScanner();
        String str= ms.randomString(20);
        //todo 文本量不足的情况下该方案无问题，不知道未来代码长度会不会超过writer的极限

        System.out.println("——————————Scan Start——————————");
        ms.scanTest();
        System.out.println("——————————Scan End——————————");
        CompilationUnit cu = StaticJavaParser.parse(new File(filename));
        //cu= Sleep.addSleep(cu,300,str);
        cu= Sleep.addSleep(cu,300);
        FileWriter writer;
        try {
            writer = new FileWriter("src/main/resources/HttpClientStatsTracker.java");
            assert cu != null;
            String temp=cu.toString();
           // temp=temp.replace("String ifdef111 = "+str+";","#ifdef A")
            //        .replace("String endif = "+str+";","#endif");
            System.out.println(temp);
            writer.write(temp);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

