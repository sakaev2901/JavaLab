use java

var productCategory = {
    name: "Smartphones"
    // products: []
}
db.productCategory.save(productCategory)

var coupon1 = {
    productCategory: new DBRef('productCategory', productCategory._id),
    name: "Coupon 1",
    description: "d1",
    percent: 20,
    code: "HELLO"
}

var coupon2 = {
    productCategory: new DBRef('productCategory', productCategory._id),
    name: "Coupon 2",
    description: "d2",
    percent: 21,
    code: "HELLO2"
}
db.coupon.save(coupon1)
db.coupon.save(coupon2)

var product = {
    name: "Iphone 12 mini",
    description: "Very tiny, light smartphone",
    price: 69999,
    productCategory: new DBRef('productCategory', productCategory._id)
}
db.product.save(product)

productCategory.products = [new DBRef('product', product._id)]
db.productCategory.save(productCategory)

var user = {
    // _id: 1,
    username: 'sakaev2901'
    // cart: undefined,
    // orders: []
}
db.user.save(user);

var cart = {
    user: new DBRef('user', user._id)
    // products: [],
    // price: 1000,
    // coupon: undefined
}
db.cart.save(cart) 

user.cart = new DBRef('cart', cart._id)
db.cart.save(cart)

cart.products = [new DBRef('product', product._id)]
cart.coupon = new DBRef('coupon', coupon1._id)
cart.price = product.price
db.cart.save(cart)

var cartCoupon = db[cart.coupon.$ref].findOne({_id: cart.coupon.$id})
var order = {
    products: [new DBRef('product', product._id)],
    date: Date(),
    price: product.price * (1 - cartCoupon.percent/ 100),
    user: new DBRef("user", user._id)
}
db.order.save(order)
user.orders = [new DBRef('order', order._id)]
db.user.save(user)

