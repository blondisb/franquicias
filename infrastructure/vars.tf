variable "aws_region" {
  description = "AWS region to deploy resources"
  type        = string
  default     = "us-east-1"
}

variable "domain" {
  description = "Domain name for the application"
  type        = string
  default     = "ddns"
  
}

variable "ecr_repository_name" {
  description = "Name for the ECR repository"
  type        = string
  default     = "franquicias-acc-repository"
}

variable "app_name" {
  description = "Name for the application"
  type        = string
  default     = "franquicias-acc"
}

variable "github_repo" {
  description = "GitHub repository URL"
  type        = string
  default     = "https://github.com/blondisb/franquicias"
}

variable "db_password" {
  description = "Database password"
  type        = string
  sensitive   = true
}

variable "key_name" {
  description = "Name of the key pair to use for SSH access"
  type        = string
  default     = "franquicias-key"
}

variable "domain_prefix" {
  description = "Subdomain prefix to use with the domain"
  type        = string
  default     = "franquicias-acc"
}