package BD1;
import com.studyopedia.*;
import Lab2.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Registration r = new Registration();
        Scanner sc = new Scanner (System.in);
        int a = -1;
        while(a !=0){
            System.out.println("1-Niit hicheeluuldiin jagsaaltiig haruulah");
            System.out.println("2-Niit mergejluudiin jagsaaltiig haruulah");
            System.out.println("3-Niit oyutnii dundaj golch dung haruulah");
            System.out.println("4-Guravaas deesh hicheeld “F” unelgee avsan hasagdah oyutnii jagsaalt");
            System.out.println("5-Hicheel bureer oyutnuudiin dungiin jagsaaltiig haruulah");
            System.out.println("6-Mergejil bureer oyutnuudiin dungiin jagsaaltiig haruulah");
            System.out.println("0-exit");

            System.out.println("Tanii songolt:");
            a = sc.nextInt();
            switch(a){
                case 1:
                    r.PrintAllHicheel();
                    break;
                case 2:
                    r.PrintAllMergejil();
                    break;
                case 3:
                    r.PrintAllGPA();
                    break;
                case 4:
                    r.PrintAll3F();
                    break;
                case 5:
                    r.PrintAllDun();
                    break;
                case 6:
                    r.PrintAllDun2();
                    break;
                case 0:
                    break;

            }
        }
    }

}

 class Subject{
    public String code;
    public String name;
    public float credit;

    public String toString(){
        return code + ","+ name + "," + credit + ".";
    }
}

 class Major{
    public String code;
    public String name;
    public String toString(){
        return code + "," + name + ",";
    }
}

 class Student{
    public String code;
    public float GPA;
    public MyChain lessons;
}

 class Lessons{
    public Subject learned;
    public int score;
}

 class Registration{
    public MyArrayLinearList studentList;
    public MyArrayLinearList subjectList;
    public MyArrayLinearList majorList;

    public Registration(){
        studentList = new MyArrayLinearList();
        subjectList = new MyArrayLinearList();
        majorList = new MyArrayLinearList();
        Read();
    }

    public void Read() {
        try {
            java.io.BufferedReader input = new java.io.BufferedReader
                    (new java.io.FileReader("./SubjectData.txt"));
            String line = input.readLine();
            while(line != null) {
                String values[] = line.split("/");
//                System.out.println(values[0]);
                line = input.readLine();
                Subject s = new Subject();
                s.code = values[0];
                s.name = values[1];
                s.credit = Float.parseFloat(values[2]);
                subjectList.Push(s);
            }
        }
        catch (Exception e) {
            System.out.println("File not found:1 ");
            System.exit(1);
        }

        try {
            java.io.BufferedReader input = new java.io.BufferedReader
                    (new java.io.FileReader("./MajorData.txt"));
            String line = input.readLine();
            while(line != null) {
                String values[] = line.split("/");
//                System.out.println(values[0]);
                line = input.readLine();
                Major m = new Major();
                m.code = values[0];
                m.name = values[1];
                majorList.Push(m);
            }
        }
        catch (Exception e) {
            System.out.println("File not found:2 ");
            System.exit(1);
        }

        try {
            java.io.BufferedReader input = new java.io.BufferedReader
                    (new java.io.FileReader("./Exam.txt"));
            String line = input.readLine();
            while(line != null) {
                String values[] = line.split("/");
//                System.out.println(values[0]);
                line = input.readLine();
                Student t = new Student();
                t.code = values[0];
                t.lessons = GetLessons(t.code);
                float gpa = 0;
                int total = 0;
                for(int i = 0; i < t.lessons.size(); i++){
                    gpa += ((Lessons)t.lessons.get(i)).score *((Lessons)t.lessons.get(i)).learned.credit;
                    total += ((Lessons)t.lessons.get(i)).learned.credit;
                }
                gpa /= total;

                if(gpa < 60) {
                    t.GPA = 0f;
                } else if (gpa < 65){
                    t.GPA = 1.0f;
                } else if (gpa < 70) {
                    t.GPA = 1.3f;
                } else if (gpa < 73) {
                    t.GPA = 1.7f;
                } else if (gpa < 77) {
                    t.GPA = 2.0f;
                } else if (gpa < 83) {
                    t.GPA = 2.3f;
                } else if (gpa < 87) {
                    t.GPA = 2.7f;
                } else if (gpa < 90) {
                    t.GPA = 3.3f;
                } else if (gpa < 95) {
                    t.GPA = 3.7f;
                } else {
                    t.GPA = 4.0f;
                }
                boolean isHave = false;
                for(int i = 0; i < studentList.size(); i++){
                    if(((Student)studentList.get(i)).code.equals(t.code)){
                        isHave = true;
                        break;
                    }
                }
                if(!isHave)
                studentList.Push(t);
            }
        }
        catch (Exception e) {
            System.out.println("File not found: 3");
            System.exit(1);
        }
    }
    public MyChain GetLessons(String code){
        MyChain c = new MyChain();
        try {
            java.io.BufferedReader input = new java.io.BufferedReader
                    (new java.io.FileReader("./Exam.txt"));
            String line = input.readLine();
            while(line != null) {
                String values[] = line.split("/");
//                System.out.println(values[0]);
                line = input.readLine();
                if(code.equals(values[0])){
                    Lessons l = new Lessons();
                    for(int j = 0; j < subjectList.size(); j++){
                        if(((Subject)subjectList.get(j)) .code.equals( values[1])){
                            l.learned = ((Subject)subjectList.get(j));
                            break;
                        }
                    }
                    l.score = Integer.parseInt(values[2]);
//                    System.out.println(Integer.parseInt(values[2]));
                    c.Add(l);
                }

            }
        }
        catch (Exception e) {
            System.out.println("File not found:4 ");
            System.exit(1);
        }
        return c;
    }
    public void PrintAllHicheel(){
        for(int i = 0; i < subjectList.size(); i++){
            System.out.println(((Subject)(subjectList.get(i))).toString());
        }
    }

    public void PrintAllMergejil(){
        for(int i = 0; i < majorList.size(); i++){
            System.out.println(((Major)(majorList.get(i))));
        }
    }

    public void PrintAllGPA(){
        for(int i = 0; i < studentList.size(); i++){
            System.out.println(((Student)studentList.get(i)).GPA);
        }
    }
    public void PrintAll3F(){
        for(int i = 0; i < studentList.size(); i++){
            int f = 0;
            for(int j = 0; j < ((Student)studentList.get(i)).lessons.size(); j++){
                Lessons l = (Lessons) (((Student)studentList.get(i)).lessons.get(j));
                if(l.score < 60){
                    f++;
                }
            }
            if (f >= 3){
                System.out.println(((Student)studentList.get(i)).code);
            }
        }
    }

    public void PrintAllDun(){
        for(int i = 0; i < subjectList.size(); i ++){
            System.out.println(((Subject) subjectList.get(i)).name);
            for(int j = 0; j < studentList.size(); j++){
                MyChain l = ((Student)studentList.get(j)).lessons;
                for(int k = 0; k < l.size(); k++){
                    if(((Lessons)l.get(k)).learned.code.equals(((Subject) subjectList.get(i)).code)){
                        System.out.println(((Student)studentList.get(j)).code + ", " + ((Lessons)l.get(k)).score);
                    }
                }

            }
        }
    }
    public void PrintAllDun2(){
        for(int i = 0; i < majorList.size(); i++){
            System.out.println(((Major) majorList.get(i)).name);
            for(int j = 0; j < studentList.size(); j++){
                String code = ((Student)studentList.get(j)).code;
                code = code.substring(0, 7);
                if (((Major) majorList.get(i)).code.equals(code)){
                    System.out.println(((Student)studentList.get(j)).code + ", " + ((Student)studentList.get(j)).GPA);
                }
            }
        }
    }
}