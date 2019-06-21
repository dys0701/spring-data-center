package org.data.center.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.data.center.config.DBContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("!@annotation(org.data.center.annotation.Master) " +
            "&& (execution(* org.data.center.repository.mysql..*.select*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.get*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.find*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.query*(..)))")
    public void readPointCut() {

    }

    @Pointcut("@annotation(org.data.center.annotation.Master) " +
            "|| execution(* org.data.center.repository.mysql..*.insert*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.add*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.update*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.edit*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.delete*(..)) " +
            "|| execution(* org.data.center.repository.mysql..*.remove*(..)) ")
    public void writePointCut() {

    }

    @Before("readPointCut()")
    public void read() {
        System.out.println("slave");
        DBContextHolder.slave();
    }

    @Before("writePointCut()")
    public void write() {
        DBContextHolder.master();
    }
}
