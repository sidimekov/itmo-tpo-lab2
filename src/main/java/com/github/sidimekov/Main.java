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
        system.getTrigModule().getSin().computeAndSaveCsv(start,end,step,"resources/Sin.csv");
        system.getTrigModule().getCos().computeAndSaveCsv(start,end,step,"resources/Cos.csv");
        system.getTrigModule().getCot().computeAndSaveCsv(start,end,step,"resources/Cot.csv");
        system.getTrigModule().getSec().computeAndSaveCsv(start,end,step,"resources/Sec.csv");
        system.getTrigModule().getCsc().computeAndSaveCsv(start,end,step,"resources/Csc.csv");

        // Модуль TrigModule
        system.getTrigModule().computeAndSaveCsv(start,end,step,"resources/TrigModule.csv");

        // Логарифмические функции
        system.getLogModule().getLn().computeAndSaveCsv(start,end,step,"resources/Ln.csv");
        system.getLogModule().getLog2().computeAndSaveCsv(start,end,step,"resources/Log2.csv");
        system.getLogModule().getLog3().computeAndSaveCsv(start,end,step,"resources/Log3.csv");

        // Модуль LogModule
        system.getLogModule().computeAndSaveCsv(start,end,step,"resources/LogModule.csv");

        // Итоговая система
        system.computeAndSaveCsv(start,end,step,"resources/MainSystem.csv");

        System.out.println("Все CSV файлы успешно созданы!");
    }
}