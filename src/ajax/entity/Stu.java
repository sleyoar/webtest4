package ajax.entity;
public class Stu {
    private int no;
    private String name;
    private int age;
    private String sex;
    private int classNo;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    @Override
    public String toString() {
        return "Stu [no=" + no + ", name=" + name + ", age=" + age + ", sex=" + sex + ", classNo=" + classNo + "]";
    }
}
