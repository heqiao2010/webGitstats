# 数据库设计

Repository

| 字段名 | 字段类型 | 说明 |
|:---|:---:|---:|
| name | String | 仓库名称 |
| dir_path | String | 目录地址 |
| addr | String | 仓库地址 |
| status | int | 状态 |
| id | bigInt | 主键 |

GitStats

| 字段名 | 字段类型 | 说明 |
|:---|:---:|---:|
| dir_path | String | 目录地址 |
| status | int | 状态 |
| r_id | bigInt | Repository主键 |
| id | bigInt | 主键 |


