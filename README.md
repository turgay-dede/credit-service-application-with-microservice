# PROJE SENARYOSU
Kullanıcı adı, soyadı, kimlik numarası, aylık geliri ve telefon numarası ile kredi başvurusu yapar. Kullanıcının kredi skoru ve aylık gelirine göre kullanıcıya kredi sonucu gösterilir. Kullanıcının kredi skoru 500 den düşük ise RED cevabı almaktadır. Kredi skoru 500-1000 arasında ve aylık geliri 5000 den düşük ise ONAYLANDI – LİMİT: 10.000 cevabı almaktadır.  Kredi skoru 500-1000 arasında ve aylık geliri 5000 den yüksek ise ONAYLANDI – LİMİT: 20.000 cevabı almaktadır. Son olarak kredi skoru 1000 den büyük veya eşit ise, ONAYLANDI – LİMİT: aylık gelir * kredi limit çarpanı = 4. Gerçekleştirilen kredi başvurusu sadece kimlik numarası ile sorgulanabilmektedir.
# PROJENIN YAPISI
Proje Microservice mimarisi yaklaşımına göre;
Customer Service, Credit Service, Service Registry, Api Gateway ve Common Service olarak ayrılmıştır.

![proje-yapisi](https://user-images.githubusercontent.com/68081349/155882270-cf79ef87-cdb8-42ec-83f0-5831f4ccffdc.png)

# Service Registry
Eureka server projeye dahil edildi. http://localhost:8761/ portunda aktifleştirildi.

![eureka](https://user-images.githubusercontent.com/68081349/155882348-4a5e19e2-792e-4335-9e02-42b5973c9ac1.png)

# Api Gateway
Customer Service ve Credit Service Api Gatewaye bağlandı. http://localhost:9191/ portu üzerinden her iki servise de erişilebilmektedir.

# Common Service
Projedeki messages ve result gibi ortak yapılar maven projesinde  tutulmuştur.

# Customer Service
CRUD işlemleri ve Kimlik numarası ile kullanıcıları sorgulama istekleri bulunmaktadır. Veri tabanı olarak Postgresql kullanılmıştır.


## API Kullanımı
```
CUSTOMER SERVICE
http://localhost:8081/rest/customers veya http://localhost:9191/rest/customers
```

#### Kullanıcı ekle


|   HTTP    |   Parametre   |      URL     |
| :-------- | :-----------  | :------------|
|    POST   | `customerDto` |     /add     |

#### Kullanıcı sil


|   HTTP    |   Parametre   |                URL                  |
| :-------- | :-----------  | :-----------------------------------|
|   DELETE  | `customerId ` |   /delete?customerId=customerId     |

#### Kullanıcı güncelle


|   HTTP    |   Parametre   |      URL     |
| :-------- | :-----------  | :----------- |
|    PUT    | `customerDto` |    /update   |

#### Tüm kullanıcıları listele


|   HTTP    |   Parametre   |      URL     |
| :-------- | :-----------  | :----------- |
|    GET    |      -        |    /getall   |


#### Kimlik numarasına göre kullanıcıları listele


|   HTTP    |    Parametre      |                         URL                                     |
| :-------- | :--------------   | :---------------------------------------------------------------|
|   GET     | `identityNumber ` |   /get/customer/identity-number?identityNumber=identityNumber   |



# Credit Service
CRUD işlemleri ve Kimlik numarası ile kullanıcıları sorgulama istekleri bulunmaktadır. Veri tabanı olarak MongoDB kullanılmıştır. Kredi başvurusunda bulunulduğunda kredi onaylanırsa kullanıcı veri tabanına kaydedilmektedir. Microservice haberleştirmesi için “Feign” kullanılmıştır.


```
CREDIT SERVICE
http://localhost:8082/rest/credits veya http://localhost:9191/rest/credits
```

#### Kredi başvuru


|   HTTP    |   Parametre   |             URL             |
| :-------- | :-----------  | :---------------------------|
|    POST   | `customerDto` |     /credit-application     |

#### Kredi sil


|   HTTP    |     Parametre     |                     URL                     |
| :-------- | :---------------  | :-------------------------------------------|
|   DELETE  | `identityNumber ` |   /delete?identityNumber=identityNumber     |

#### Kredi güncelle


|   HTTP    |   Parametre   |      URL     |
| :-------- | :-----------  | :----------- |
|    PUT    | `creditDto`   |    /update   |

#### Tüm kredileri listele


|   HTTP    |   Parametre   |      URL     |
| :-------- | :-----------  | :----------- |
|    GET    |      -        |    /getall   |


#### Kimlik numarasına göre kredileri listele


|   HTTP    |    Parametre      |                         URL                                     |
| :-------- | :--------------   | :---------------------------------------------------------------|
|   GET     | `identityNumber ` |   /get/credit/identity-number?identityNumber=identityNumber     |

