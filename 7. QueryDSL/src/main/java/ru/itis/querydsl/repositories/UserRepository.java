package ru.itis.querydsl.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.querydsl.models.QUser;
import ru.itis.querydsl.models.User;

public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
//    @Override
//    default void customize(QuerydslBindings querydslBindings, QUser qUser) {
//        querydslBindings.bind(qUser.name).first(
//                StringExpression::containsIgnoreCase
//        );
//        querydslBindings.bind(qUser.cars.any().model).as("cars.model").first(
//                StringExpression::containsIgnoreCase
//        );
//
//    }
}
