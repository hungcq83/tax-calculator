terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 3.20.0"
    }
    sql = {
      source = "paultyng/sql"
      version = "0.4.0"
    }
  }
}
