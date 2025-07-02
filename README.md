# Finance-management
Finance management with android jetpack compose, mvvm, clean architecture, roomdb

# Tổng quan
Clean Architecture có vai trò tạo cây phụ thuộc và chia cấu trúc project thành 3 lớp chính:
    + Data layer:  có vai trò xử lý logic, thao tác với data và là tầng duy nhất biết về các Retrofit, Room,...
    + Domain layer: là trung tâm, có vai trò định nghĩa logic nghiệp vụ và không phụ thuộc vào bất kỳ thư viện nào
    + Presentation layer: UI hiển thị 

MVVM có vai trò tách biệt UI và xử lý logic:
    -> ViewModel xử lý dữ liệu là Model, View quan sát đến dữ liệu nếu có thay đổi thì update lại UI 

# Brainstorm
~> Xác định các model ban đầu: Collect, CollectType, Payout, PayoutType    

~> Định nghĩa Domain layer, ở đây Repository là lớp trừu tượng định nghĩa các Function liên quan đến Model (vd: thêm sửa xóa ...)
    Mỗi Function của 1 Model được định nghĩa sẽ tương ứng với 1 UseCase và invoke bởi UseCase 

~> Sau khi hoàn thành Domain, định nghĩa tiếp layer Data, ở đây Repository Implement kế thừa Repository từ Domain,
    sẽ chịu trách nhiệm xử lý logic và trả về kết quả theo chức năng được định nghĩa bởi Repository, 
    vì Repository Implement kế thừa từ Repository, nên Hilt sẽ inject nó lại vào UseCase của Domain, 
    khi UseCase invoke sẽ triển khai xử lý logic của Repository Implement

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