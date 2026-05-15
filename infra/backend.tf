terraform {
  backend "azurerm" {
    resource_group_name  = "rg-tfstate"
    storage_account_name = "tfstateivtsys29842"
    container_name       = "tfstate"
    key                  = "terraform.tfstate"
  }
}
