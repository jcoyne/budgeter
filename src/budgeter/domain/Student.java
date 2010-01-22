/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.domain;
import javax.persistence.*;
import org.hibernate.*;
import budgeter.Util;
import java.util.*;
/**
 *
 * @author jcoyne
 */
@Entity
@Table(name = "students")
public class Student {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    public Student() {
    }

    public Student(String first_name, String last_name) {
        this.lastName = last_name;
        this.firstName = first_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

 

    public static void createStudent(String first, String last) {
        // First unit of work
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student s = new Student(first, last);
        Long msgId = (Long) session.save(s);
        tx.commit();
        session.close();

    }

    public static String[] getStudentNames () {
        List<String> strList = new ArrayList<String>();
        // Second unit of work
        Session newSession =
            Util.getSessionFactory().openSession();
        Transaction newTransaction = newSession.beginTransaction();
        List students =
            newSession.createQuery("from Student m order by m.lastName asc").list();
        System.out.println( students.size() +
            " students(s) found:" );
        for ( Iterator iter = students.iterator();
              iter.hasNext(); ) {
            Student student = (Student) iter.next();
            System.out.println( student.getLastName() );
            strList.add(student.getFirstName() + " " + student.getLastName());
        }
        newTransaction.commit();
        newSession.close();
        return strList.toArray(new String[0]);
    }
}
