package com.demos.jpaassessment;

import java.util.*;

public class DaoClient {

    static OrderDao dao = new OrderDaoImpl();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        String opt = null;

        do {

            System.out.println("Choose Operation:");
            System.out.println("1 -> Add New Order");
            System.out.println("2 -> Display Order By ID");
            System.out.println("3 -> Display Orders By Customer Name");

            int mtype = scan.nextInt();

            processMenu(mtype);

            System.out.println("Do you want to continue? (y/n)");

            opt = scan.next();

        } while(opt.equalsIgnoreCase("y"));
    }

    public static void processMenu(int mtype) {

        switch(mtype) {

        case 1:
            addOrder();
            break;

        case 2:
            viewOrderByOrderID();
            break;

        case 3:
            viewOrdersByCustName();
            break;

        default:
            System.out.println("Please select a valid option.");
        }
    }

    public static void addOrder() {

        Order o = new Order();

        System.out.println("Provide Customer ID:");
        int cid = scan.nextInt();

        System.out.println("Provide Order Amount:");
        double amt = scan.nextDouble();

        o.setOrder_amt(amt);
        o.setOrder_date(new Date());

        dao.addOrder(o, cid);

        System.out.println("Order saved successfully.");
    }

    public static void viewOrderByOrderID() {

        System.out.println("Enter the Order ID:");

        int id = scan.nextInt();

        Order o = dao.viewOrderById(id);

        if(o!=null)
        {
            System.out.println("----- Order Details -----");
            System.out.println("Order ID : "+o.getOrder_id());
            System.out.println("Order Amount : "+o.getOrder_amt());
            System.out.println("Order Date : "+o.getOrder_date());
        }
        else
        {
            System.out.println("No order exists with the given ID.");
        }
    }

    public static void viewOrdersByCustName() {

        System.out.println("Enter Customer Name:");

        String name = scan.next();

        List<Order> list = dao.viewOrdersByCustomerName(name);

        if(list.size()==0)
        {
            System.out.println("No orders found for this customer.");
        }
        else
        {
            System.out.println("Orders for customer: "+name);

            for(Order o : list)
            {
                System.out.println("-------------------------");
                System.out.println("Order ID : "+o.getOrder_id());
                System.out.println("Amount : "+o.getOrder_amt());
                System.out.println("Date : "+o.getOrder_date());
            }
        }
    }
}