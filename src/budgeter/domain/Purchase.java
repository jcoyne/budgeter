/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.domain;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import budgeter.Util;

/**
 *
 * @author jcoyne
 */
@Entity
@Table(name="purchases")
public class Purchase {

    @Id @GeneratedValue
    private Long id;

    @Column(name="note")
    private String note;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Purchase() {
    }

    public Purchase(String note, BigDecimal amount, Date date, Account account) {
        this.note = note;
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    

    public static void createPurchase(String note, Date date, BigDecimal amount, Account account) {
        save(new Purchase(note, amount, date, account));
    }

    public static void createPurchase(String note, String date, String amount, Object account) throws ParseException {
        createPurchase(note, DateFormat.getDateInstance(SimpleDateFormat.SHORT).parse(date), new BigDecimal(amount), (Account) account);
    }

    public static void save(Purchase p) {
        Session sess = Util.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        sess.save(p);
        tx.commit();
        sess.close();

    }

    public static void update(Purchase p) {
        Session sess = Util.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        sess.update(p);
        tx.commit();
        sess.close();

    }


    public static List<Purchase> getPurchases() {
        Session newSession =Util.getSessionFactory().openSession();
        Transaction newTransaction = newSession.beginTransaction();
        List<Purchase> purchases =
            newSession.createQuery("from Purchase m order by m.date asc").list();
        newTransaction.commit();
        newSession.close();
        return purchases;
    }

    public static BigDecimal sum(Collection<Purchase> l) {
        BigDecimal aggregator = new BigDecimal(0);
        for (Purchase p : l) {
            aggregator = aggregator.add(p.getAmount());
        }
        return aggregator;
    }

}
