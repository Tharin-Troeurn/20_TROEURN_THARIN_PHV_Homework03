public class SalariesEmployee extends StaffMember{
    private double salary;
    private double bunus;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBunus() {
        return bunus;
    }

    public void setBunus(double bunus) {
        this.bunus = bunus;
    }

    public SalariesEmployee(int id, String name, String address, double salary, double bunus) {
        super(id, name, address);
        this.salary = salary;
        this.bunus = bunus;
    }

    @Override
    public String toString() {
        return "SalariesEmployee{" +
                "salary=" + salary +
                ", bunus=" + bunus +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public double pay(){
        return salary+bunus;
    }

}
