/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.domain;

/**
 *
 * @author jcoyne
 */
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.*;
import budgeter.Util;
@Entity
@Table(name="accounts")
public class Account {
    @Id @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Set<Purchase> purchases;

    public Account(String name) {
        this.name = name;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }




    @Override
    public String toString() {
        return getName();
    }

    public static void createAccount(String str) {

        Session sess = Util.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        sess.save(new Account(str));
        tx.commit();
        sess.close();

    }

    public static List<Account> getAccounts () {
        Session sess = Util.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        List accounts = sess.createQuery("from Account order by name asc").list();
        tx.commit();
        return accounts;
    }
}
