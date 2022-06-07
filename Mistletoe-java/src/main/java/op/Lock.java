package op;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    public static CompilationUnit addLock(CompilationUnit cu){
        ReentrantLock lock=new ReentrantLock();
        try {
            ClassOrInterfaceDeclaration hw = cu.getClassByName("hw").get();

            List<MethodDeclaration> x= hw.getMethods();
            String st1 = "ReentrantLock lock=new ReentrantLock();";
            String st2 = "lock.lock();" ;
            String st3 = "lock.unlock();//解锁";
            for(MethodDeclaration md:x){
                System.out.println(md.getName());
                md.getBody().get()
                        .addStatement(0,StaticJavaParser.parseStatement(st1))
                        .addStatement(1,StaticJavaParser.parseStatement(st2))
                        .addStatement(2,StaticJavaParser.parseStatement(st3));
                System.out.println(md);
            }
            return cu;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static CompilationUnit addLock(CompilationUnit cu,String random){
        ReentrantLock lock=new ReentrantLock();
        try {
            ClassOrInterfaceDeclaration hw = cu.getClassByName("hw").get();

            List<MethodDeclaration> x= hw.getMethods();
            String st1 = "ReentrantLock lock=new ReentrantLock();";
            String st2 = "lock.lock();" ;
            String st3 = "lock.unlock();//解锁";
            String st4 = "String ifdef111 = "+random+";";
            String st5 = "String endif = "+random+";";
            for(MethodDeclaration md:x){
                System.out.println(md.getName());
                md.getBody().get()
                        .addStatement(0,StaticJavaParser.parseStatement(st5))
                        .addStatement(0,StaticJavaParser.parseStatement(st3))
                        .addStatement(0,StaticJavaParser.parseStatement(st2))
                        .addStatement(0,StaticJavaParser.parseStatement(st1))
                        .addStatement(0,StaticJavaParser.parseStatement(st4));
                System.out.println(md);
            }
            return cu;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
