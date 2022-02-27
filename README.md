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

Kullanıcı ekleme: http://localhost:8081/rest/customers/add veya http://localhost:9191/rest/customers/add

Kullanıcı silme: http://localhost:8081/rest/customers/delete?customerId=xxxx veya http://localhost:9191/rest/customers/delete?customerId=xxxx

Kullanıcı güncelleme: http://localhost:8081/rest/customers/update veya http://localhost:9191/rest/customers/

Tüm kullanıcıları listeleme: http://localhost:8081/rest/customers/getall veya http://localhost:9191/rest/customers/getall

Kimlik numarasına göre kullanıcıları listeleme: http://localhost:8081/rest/customers/get/customer/identity-number?identityNumber=xxx veya http://localhost:9191/rest/customers/get/customer/identity-number?identityNumber=xxx

# Credit Service
CRUD işlemleri ve Kimlik numarası ile kullanıcıları sorgulama istekleri bulunmaktadır. Veri tabanı olarak MongoDB kullanılmıştır. Kredi başvurusunda bulunulduğunda kredi onaylanırsa kullanıcı veri tabanına kaydedilmektedir. Microservice haberleştirmesi için “Feign” kullanılmıştır.

Hesap ekleme: http://localhost:8082/rest/credits/credit-application veya  http://localhost:9191/rest/credits/credit-application

Hesap silme: http://localhost:8082/rest/credits/delete?identityNumber=xxx veya  http://localhost:9191/rest/credits/delete?identityNumber=xxx 

Hesap güncelleme: http://localhost:8082/rest/credits/update veya  http://localhost:9191/rest/credits/update 

Tüm hesap listeleme: http://localhost:8082/rest/credits/getall veya  http://localhost:9191/rest/credits/getall 

Kimlik numarasına göre hesapları listeleme: http://localhost:8082/rest/credits/get/credit/identity-number?identityNumber=xxx veya http://localhost:9191/rest/credits/get/credit/identity-number?identityNumber=xxx  


