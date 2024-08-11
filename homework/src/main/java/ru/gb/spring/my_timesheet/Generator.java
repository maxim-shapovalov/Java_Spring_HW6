package ru.gb.spring.my_timesheet;

import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private final String[] lastNameMaleBase = new String[]{
            "Иванов", "Петров", "Тарасов", "Владимиров", "Сидоров", "Дмитриев", "Степанов", "Никитин", "Андреев", "Егоров"
    };
    private final String[] lastNameFemaleBase = new String[]{
            "Иванова", "Петрова", "Тарасова", "Владимирова", "Сидорова", "Дмитриева", "Степанова", "Никитина", "Андреева", "Егорова"
    };
    private final String[] firstNameMaleBase = new String[]{
            "Иван", "Петр", "Тарас", "Владимир", "Сидор", "Дмитрий", "Степан", "Никита", "Андрей", "Егор"
    };
    private final String[] firstNameFemaleBase = new String[]{
            "Инна", "Полина", "Татьяна", "Виктория", "Светлана", "Дарья", "София", "Наталья", "Алла", "Екатерина"
    };
    private final String[] departmentsBase = new String[]{
            "Отдел продаж", "Отдел маркетинга", "Юридический отдел" //, "Бухгалтерия", "Отдел качества"
    };

    public int getRandomInt(int bound){
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public String generateMaleLastName(){
        return lastNameMaleBase[getRandomInt(lastNameMaleBase.length)];
    }

    public String generateMaleFirstName(){
        return firstNameMaleBase[getRandomInt(firstNameMaleBase.length)];
    }

    public String generateFemaleLastName(){
        return lastNameFemaleBase[getRandomInt(lastNameMaleBase.length)];
    }

    public String generateFemaleFirstName(){
        return firstNameFemaleBase[getRandomInt(firstNameFemaleBase.length)];
    }

    public String generatePhone(){
        String phone = "";
        for (int i = 0; i < 8; i++) {
            phone+=getRandomInt(10);
        }
        return phone;
    }
    public String generateDepartment(){
        return departmentsBase[getRandomInt(departmentsBase.length)];
    }

}

