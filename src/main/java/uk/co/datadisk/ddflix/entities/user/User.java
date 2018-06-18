package uk.co.datadisk.ddflix.entities.user;

import lombok.*;
import org.hibernate.annotations.Where;
import uk.co.datadisk.ddflix.entities.film.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"roles", "passwordResetToken", "ratings", "reviews"}, callSuper = false)
@ToString(exclude = {"roles", "passwordResetToken", "ratings", "reviews"})
@Entity
@Table(name = "users")
public class User extends UserDetail {

    @Builder
    public User(Long id, boolean locked, boolean active, boolean expired, boolean enabled, String email, String username, String password) {
        super(id, locked, active, expired, enabled);
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @NotNull
    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    // Lazy loaded by default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PasswordResetToken passwordResetToken;

    @OneToOne
    @JoinColumn(name = "defaultShippingAddress")
    private Address defaultShippingAddress;

    @OneToOne
    @JoinColumn(name = "defaultBillingAddress")
    private Address defaultBillingAddress;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name="user_shipping_address",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="address_id")}
    )
    private Set<Address> shippingAddresses = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserImages userImages;

    // By default ASC order will be returned, oldest first
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("wishedOn ASC")
    private List<Wishlist> wishlists = new ArrayList<>();

    // added a where clause as I don't want the returned discs/films
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "returned_date IS NULL")
    @OrderBy("sent_date ASC")
    private List<FilmsAtHome> filmsAtHomes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public void addRole(Role role) { this.roles.add(role);}
    public void removeRole(Role role) { this.roles.remove(role);}

    public void addShippingAddress(Address shippingAddress) { this.shippingAddresses.add(shippingAddress);}
    public void removeShippingAddress(Address shippingAddress) { this.shippingAddresses.remove(shippingAddress);}

    public void addFilmToWishList(Film film) {
        if(!checkFilmInWishlist(film)) {
            wishlists.add(new Wishlist(this, film));
        } else {
            System.out.println("You already have " + film.getTitle() + " in your wishlist");
        }
    }

    public void removeFilmFromWishlist(Film film) {
        if(checkFilmInWishlist(film)){
            wishlists.remove(new Wishlist(this, film));
        } else {
            System.out.println("You don't have " + film.getTitle() + " in your wishlist");
        }
    }

    public boolean checkFilmInWishlist(Film film) {
        return wishlists.contains(new Wishlist(this, film));
    }

    public void addRating(Film film, Integer rating) {
        if(!checkRating(film)) {
            ratings.add(new Rating(this, film, rating));
        } else {
            System.out.println("You already have a rating for " + film.getTitle());
        }
    }

    public void removeRating(Film film) {
        if(checkRating(film)){
            ratings.remove(new Rating(this, film));
        } else {
            System.out.println("You don't have a rating for " + film.getTitle());
        }
    }

    public boolean checkRating(Film film) {
        return ratings.contains(new Rating(this, film));
    }

    public void addReview(Film film, String review) {
        if(!checkReview(film)) {
            reviews.add(new Review(this, film, review));
        } else {
            System.out.println("You already have a review for " + film.getTitle());
        }
    }

    public void removeReview(Film film) {
        if(checkReview(film)){
            reviews.remove(new Review(this, film));
        } else {
            System.out.println("You don't have a review for " + film.getTitle());
        }
    }

    public boolean checkReview(Film film) {
        return reviews.contains(new Review(this, film));
    }

    // check the @OrderBy above (default is ASC
    public List<Wishlist> getSortedWishlistDesc(){
        return wishlists.stream().sorted(Comparator.comparing(Wishlist::getWishedOn).reversed()).collect(Collectors.toList());
    }
}