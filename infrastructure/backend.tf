terraform {
  backend "s3" {
    bucket = "blondis-terraform-state"
    key    = "terraform.tfstate"
    region = "us-east-1"
  }
}
