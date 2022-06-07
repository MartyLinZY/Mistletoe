package op;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Sleep {
    public static  CompilationUnit addSleep(CompilationUnit cu){
        return addSleep(cu,1000);
    }

    public static CompilationUnit addSleep(CompilationUnit cu,int n){
        ReentrantLock lock=new ReentrantLock();
        try {
            ClassOrInterfaceDeclaration hw = cu.getClassByName("hw").get();

            List<MethodDeclaration> x= hw.getMethods();
            String st = "Thread.sleep("+n+");";

            String st1 = "";
            for(MethodDeclaration md:x){
                System.out.println(md.getName());
                if(md.getName().asString().toLowerCase().contains("connect")){
                    md.getBody().get()
                            .addStatement(0,StaticJavaParser.parseStatement(st));
                    System.out.println(md);
                }
              /*  if(md.getName().asString().contains("connect")){
                    md.getBody().get()
                            .addStatement(0,StaticJavaParser.parseStatement(st1));
                    System.out.println(md);
                }*/
            }
            return cu;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static CompilationUnit addSleep(CompilationUnit cu,int n,String random){
        ReentrantLock lock=new ReentrantLock();
        try {
            ClassOrInterfaceDeclaration hw = cu.getClassByName("hw").get();

            List<MethodDeclaration> x= hw.getMethods();
            String st = "Thread.sleep("+n+");";
            String st1 = "String ifdef111 = "+random+";";
            String st2 = "String endif = "+random+";";
            for(MethodDeclaration md:x){
                System.out.println(md.getName());
                if(md.getName().asString().toLowerCase().contains("connect")){
                    md.getBody().get()
                            .addStatement(0,StaticJavaParser.parseStatement(st2))
                            .addStatement(0,StaticJavaParser.parseStatement(st))
                            .addStatement(0,StaticJavaParser.parseStatement(st1));
                    System.out.println(md);
                }
              /*  if(md.getName().asString().contains("connect")){
                    md.getBody().get()
                            .addStatement(0,StaticJavaParser.parseStatement(st1));
                    System.out.println(md);
                }*/
            }
            return cu;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new File("src/main/resources/hw.java"));
        Class<? extends CompilationUnit> test=cu.getClass();

    }
}
