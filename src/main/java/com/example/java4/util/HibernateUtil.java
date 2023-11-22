//package com.example.java4.util;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Environment;
//
//import java.util.Properties;
//
//public class HibernateUtil<ServiceRegistry> {
//    private static final SessionFactory FACTORY;
//
//    static {
//        Configuration conf = new Configuration();
//        Properties properties = new Properties();
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
//        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=SD18203");
//        properties.put(Environment.USER, "sa");
//        properties.put(Environment.PASS, "Password.1");
//        properties.put(Environment.SHOW_SQL, "true");
//        conf.setProperties(properties);
//        conf.addAnnotatedClass(SinhVien.class);
//        conf.addAnnotatedClass(LopHoc.class);
//        ServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .applySettings(conf.getProperties()).build();
//        FACTORY = conf.buildSessionFactory(registry);
//    }
//
//    public static SessionFactory getFACTORY() {
//        return FACTORY;
//    }
//    public static void main(String[] args) {
//        System.out.println(getFACTORY());
//    }
//}
//
