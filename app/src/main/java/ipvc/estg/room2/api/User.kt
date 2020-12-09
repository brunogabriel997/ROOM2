package ipvc.estg.room2.api
/*
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val address: Address
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)

// Alterar conforme a minha Database
*/


data class User(
    val Id: String,
    val Descricao: String,
    val Username: String,
    val lat: String,
    val lng: String,
    val Tipo: String
)

data class iduser(
        val id: String?
)



