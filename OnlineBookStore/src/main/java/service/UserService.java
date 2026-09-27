package service;

import entity.Users;
import entity.UserAddresses;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "onlinebookPU")
    private EntityManager em;

    // 🔷 REGISTER USER
    public String register(Users user) {
        try {
            // 🔷 TRIM INPUTS (IMPORTANT)
            String name = user.getFullname() != null ? user.getFullname().trim() : "";
            String email = user.getEmail() != null ? user.getEmail().trim() : "";
            String password = user.getPassword() != null ? user.getPassword().trim() : "";
            String phone = user.getPhone() != null ? user.getPhone().trim() : "";

            // 🔷 VALIDATION
            if (name.isEmpty()) {
                return "Name is required";
            }

            if (email.isEmpty()) {
                return "Email is required";
            }

            if (password.isEmpty()) {
                return "Password is required";
            }

            if (phone.isEmpty()) {
                return "Phone number is required";
            }

            // 🔷 EMAIL FORMAT
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Invalid email format (example: abc@gmail.com)";
            } 

            // 🔷 PASSWORD LENGTH
            if (password.length() < 4) {
                return "Password must be at least 4 characters";
            }

            // 🔷 PHONE VALIDATION
            if (!phone.matches("\\d{10}")) {
                return "Phone must be 10 digits";
            }

            // 🔷 CHECK EMAIL EXISTS
            Long count = em.createQuery(
                    "SELECT COUNT(u) FROM Users u WHERE u.email = :email",
                    Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (count > 0) {
                return "Email already registered";
            }

            // 🔷 SET CLEAN VALUES BACK
            user.setFullname(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);

            // 🔷 SAVE USER
            em.persist(user);

            return "Registration successful";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error in registration";
        }
    }

    // 🔷 LOGIN USER
    public Users login(String email, String password) {
        try {
            if (email == null || password == null) {
                return null;
            }

            email = email.trim();
            password = password.trim();

            if (email.isEmpty() || password.isEmpty()) {
                return null;
            }

            return em.createQuery(
                    "SELECT u FROM Users u WHERE u.email = :email AND u.password = :password",
                    Users.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null; // invalid login
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 🔷 UPDATE PROFILE
    public String updateProfile(Users user) {
        try {
            em.merge(user);
            return "Profile updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating profile";
        }
    }

    // 🔷 ADD ADDRESS
    public String addAddress(UserAddresses address) {
        try {
            if (address.getUserId() == null) {
                return "User required";
            }

            if (address.getFullAddress() == null || address.getFullAddress().trim().isEmpty()) {
                return "Address required";
            }

            em.persist(address);
            return "Address added successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error adding address";
        }
    }

    // 🔷 GET USER ADDRESSES
    public List<UserAddresses> getAddressesByUser(int userId) {
        try {
            return em.createQuery(
                    "SELECT a FROM UserAddresses a WHERE a.userId.userId = :uid",
                    UserAddresses.class)
                    .setParameter("uid", userId)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 🔷 GET USER BY ID
    public Users getUserById(int id) {
        try {
            return em.find(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}