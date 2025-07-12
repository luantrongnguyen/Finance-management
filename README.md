# Finance-management
Finance management with android jetpack compose, mvvm, clean architecture, roomdb

# Tổng quan
Clean Architecture có vai trò tạo cây phụ thuộc và chia cấu trúc project thành 3 lớp chính:
    + Data layer:  có vai trò xử lý logic, thao tác với data và là tầng duy nhất biết về các Retrofit, Room,...
    + Domain layer: là trung tâm, có vai trò định nghĩa logic nghiệp vụ và không phụ thuộc vào bất kỳ thư viện nào
    + Presentation layer: Là tầng UI – nơi hiển thị dữ liệu ra màn hình. Tầng này chỉ quan tâm đến việc render dữ liệu và gửi các action của người dùng về ViewModel để xử lý. 

MVVM có vai trò tách biệt UI và xử lý logic:
    ->  ViewModel là nơi xử lý dữ liệu, gọi các UseCase và cung cấp dữ liệu để UI quan sát.
    ->  View (Compose UI) quan sát dữ liệu từ ViewModel. Khi dữ liệu thay đổi, UI sẽ tự động được cập nhật thông qua cơ chế reactive.



# Brainstorm

Step 1:
~> Xác định các Entity trong ứng dụng, ví dụ trong 1 ứng dụng quản lý tài chính cơ bản sẽ có: Collect, CollectType, Payout, PayoutType    

Step 2:
~> Định nghĩa Domain layer, ở đây Repository là lớp trừu tượng định nghĩa các Function liên quan đến Model (vd: thêm sửa xóa ...)
    Mỗi Function của 1 Model được định nghĩa sẽ tương ứng với 1 UseCase và invoke bởi UseCase 

Step 3:
~> Sau khi hoàn thành Domain, định nghĩa tiếp layer Data, ở đây Repository Implement kế thừa Repository từ Domain,
    sẽ chịu trách nhiệm xử lý logic và trả về kết quả theo chức năng được định nghĩa bởi Repository, 
    vì Repository Implement kế thừa từ Repository, nên Hilt sẽ inject nó lại vào UseCase của Domain, 
    khi UseCase invoke sẽ triển khai xử lý logic của Repository Implement

Step 4:
~> Sau khi định nghĩa xong Domain và Data, tại layer Presentation, định nghĩa các ViewModel, mỗi Model sẽ tương ứng 1 ViewModel,
    và các UseCase sẽ được triển khai ở trong ViewModel để update dữ liệu, View sẽ quan sát các dữ liệu này để cập nhật lên UI

├── data
│   ├── model
│   ├── remote
│   └── repository
├── domain
│   ├── model
│   ├── repository
│   └── usecase
├── presentation
│   ├── ui
│   └── viewmodel
└── di