output "ecr_repository_url" {
  description = "URL del repositorio ECR"
  value       = aws_ecr_repository.app_repo.repository_url
}

output "db_endpoint" {
  description = "Endpoint de la base de datos"
  value       = aws_db_instance.app_db.endpoint
}

output "ecs_cluster_name" {
  description = "Nombre del cluster ECS"
  value       = aws_ecs_cluster.app_cluster.name
}

output "ecs_task_definition" {
  description = "ARN de la definición de tarea ECS"
  value       = aws_ecs_task_definition.app_task.arn
}

output "ecs_service_name" {
  description = "Nombre del servicio ECS"
  value       = aws_ecs_service.app_service.name
}

output "key_name" {
  description = "Nombre del key pair para acceso SSH"
  value       = aws_key_pair.app_key_pair.key_name
}

output "private_key_file" {
  description = "Archivo de clave privada generado"
  value       = local_file.private_key_pem.filename
}