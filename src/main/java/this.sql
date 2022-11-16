-- Clock_data.`center`
create table if not exists Clock_data.`center`
(
    `timeOne` datetime not null comment '时间',
    `dataOne` varchar(255) not null comment '事件名',
    `randomOne` varchar(255) not null comment '绑定随机数'
    ) comment 'Clock_data.`center`';