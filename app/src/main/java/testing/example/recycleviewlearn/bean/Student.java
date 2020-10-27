package testing.example.recycleviewlearn.bean;


/*
* 可以看到，变种 Builder 模式包括以下内容：
*在要构建的类内部创建一个静态内部类 Builder
*静态内部类的参数与构建类一致
*构建类的构造参数是 静态内部类，使用静态内部类的变量一一赋值给构建类
*静态内部类提供参数的 setter 方法，并且返回值是当前 Builder 对象
*最终提供一个 build 方法构建一个构建类的对象，参数是当前 Builder 对象
*
*
* 优点
*在建造者模式中， 客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象
*每一个具体建造者都相对独立，而与其他的具体建造者无关，因此可以很方便地替换具体建造者或增加新的具体建造者， 用户使用不同的具体建造者即可得到不同的产品对象
*可以更加精细地控制产品的创建过程 。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程
*增加新的具体建造者无须修改原有类库的代码，指挥者类针对抽象建造者类编程，系统扩展方便，符合“开闭原则”
*缺点
*建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。
*如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大
*
* */

public class Student {
    private String name;
    private String age;
    private String sex;

    private Student(Builder builder) {
        name = builder.name;
        age = builder.age;
        sex = builder.sex;
    }

    public static Student.Builder builder() {
        return new Student.Builder();
    }

    public static class Builder {
        private String name;
        private String age;
        private String sex;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Student build() {
            return new Student(this);
        }

    }

}
