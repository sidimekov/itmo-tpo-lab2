package com.github.sidimekov;

import com.github.sidimekov.functionSystem.MainSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Start: "); double start = sc.nextDouble();
        System.out.print("End: "); double end = sc.nextDouble();
        System.out.print("Step: "); double step = sc.nextDouble();
        System.out.print("Eps: "); double eps = sc.nextDouble();

        MainSystem system = new MainSystem(eps);

        // Тригонометрические функции
        system.getTrigModule().getSin().computeAndSaveCsv(start,end,step,"result/Sin.csv");
        system.getTrigModule().getCos().computeAndSaveCsv(start,end,step,"result/Cos.csv");
        system.getTrigModule().getCot().computeAndSaveCsv(start,end,step,"result/Cot.csv");
        system.getTrigModule().getSec().computeAndSaveCsv(start,end,step,"result/Sec.csv");
        system.getTrigModule().getCsc().computeAndSaveCsv(start,end,step,"result/Csc.csv");

        // Модуль TrigModule
        system.getTrigModule().computeAndSaveCsv(start,end,step,"result/TrigModule.csv");

        // Логарифмические функции
        system.getLogModule().getLn().computeAndSaveCsv(start,end,step,"result/Ln.csv");
        system.getLogModule().getLog2().computeAndSaveCsv(start,end,step,"result/Log2.csv");
        system.getLogModule().getLog3().computeAndSaveCsv(start,end,step,"result/Log3.csv");

        // Модуль LogModule
        system.getLogModule().computeAndSaveCsv(start,end,step,"result/LogModule.csv");

        // Итоговая система
        system.computeAndSaveCsv(start,end,step,"result/MainSystem.csv");

        System.out.println("Все CSV файлы успешно созданы!");
    }
}